import { Fragment } from 'react';
import useInput from '../../hooks/use-input';
import style from './Login.module.css'
import { urlConfig } from '../../urlConfig';
import { useHistory } from "react-router-dom";
import { useDispatch } from 'react-redux';
import { authActions } from '../../store/auth';

const isNotEmpty = (value) => value.trim() !== '';
const isEmail = (value) => value.includes('@');

const Login = (props) => {
  const history = useHistory()
  const dispatch = useDispatch();

    const {
        value: emailValue,
        isValid: emailIsvalid,
        hasError: emailHasError,
        valueChangeHandler: emailChangeHandler,
        inputBlurHandler: emailBlurHandler
    } = useInput(isEmail);

    const {
        value: passwordValue,
        isValid: passwordIsValid,
        hasError: passwrodHasError,
        valueChangeHandler: passwordChangeHandler,
        inputBlurHandler: passwordBlurHandler
    } = useInput(isNotEmpty);

    let formIsValid = false;

    if(emailIsvalid && passwordIsValid){
        formIsValid = true;
    }

    const emailClass = emailHasError ? 'form-control-invalid' : 'form-control';
    const passwordClass = passwrodHasError ? 'form-control-invalid' : 'form-control';
    

    const submitHandler = event =>{
        event.preventDefault();

        if (formIsValid){
            login();
            return;
        }
    }

    const login = () => {
    const LoginDTO = { email: emailValue, password: passwordValue}
    const requestOptions = {
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
      method: "POST",
      body: JSON.stringify(LoginDTO),
    };

    fetch(`${urlConfig.trainingUrl}/api/users/login`, requestOptions)
      .then((response) => {
        if (!response.ok) {
          return Promise.reject(response);
        }
        return response.json();
      })
      .then((data) => {
        console.log(data)
        localStorage.setItem("user", JSON.stringify(data));
        const role = JSON.parse(localStorage.getItem('user')).role
        dispatch(authActions.login(role));

        history.push("/home")
        window.location.reload()
      })
      .catch((error) => {
        console.log(error);
      });
    }

  

    return (
        <Fragment>
        <div className={style.backdrop} onClick={props.onHide}></div>
        <div className={style.modal}>
        <section className={style.login}>
            <form onSubmit={submitHandler}>
               <div className={style[emailClass]}>
                   <label htmlFor='email'>Your email</label>
                   <input 
                    type='email'
                    value={emailValue}
                    onChange={emailChangeHandler}
                    onBlur={emailBlurHandler}
                   />
                   {emailHasError && <p className={style.error}>Please enter valid email</p>}
                </div> 
                <div className={style[passwordClass]}>
                   <label htmlFor='password'>Your password</label>
                   <input
                    type='password'
                    onChange={passwordChangeHandler}
                    onBlur={passwordBlurHandler}
                    />
                   {passwrodHasError && <p className={style.error}>Please enter valid password</p>}
                </div>
                <button className={style.button} onClick={props.onHide}>
                    Close
                </button>
                <button className={style.button}>
                    Login
                </button>
            </form>      
        </section>
        </div>
        </Fragment>
    )

}

export default Login;