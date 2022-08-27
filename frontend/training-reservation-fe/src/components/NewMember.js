import { useState } from 'react';
import style from './NewMember.module.css'
import { urlConfig } from '../urlConfig';
import useInput from '../hooks/use-input';

const isNotEmpty = (value) => value.trim() !== '';
const isEmail = (value) => value.includes('@');

const NewMember = () => {
  const [phoneNumber, setPhoneNumber] = useState("");
  const [typeOfTraining, setTypeOfTraining] = useState("");
  const [typeOfUser, setTypeOfUser] = useState("");

  const {
    value: name,
    isValid: nameIsValid,
    hasError: nameHasError,
    valueChangeHandler: nameChangeHandler,
    inputBlurHandler: nameBlurHandler
  } = useInput(isNotEmpty);

  const {
    value: lastName,
    isValid: lastNameIsValid,
    hasError: lastNameHasError,
    valueChangeHandler: lastNameChangeHandler,
    inputBlurHandler: lastNameBlurHandler
  } = useInput(isNotEmpty);

  const {
    value: email,
    isValid: emailIsvalid,
    hasError: emailHasError,
    valueChangeHandler: emailChangeHandler,
    inputBlurHandler: emailBlurHandler
  } = useInput(isEmail);


  let formIsValid = false;

  if (nameIsValid && lastNameIsValid && emailIsvalid) {
    formIsValid = true;
  }
  const nameClass = nameHasError ? 'form-control-invalid' : 'form-control';
  const lastNameClass = lastNameHasError ? 'form-control-invalid' : 'form-control';
  const emailClass = emailHasError ? 'form-control-invalid' : 'form-control';

  const onSubmit = (event) => {
    event.preventDefault();
    console.log(formIsValid);
    if (formIsValid) {
      createUser();
      return;
    }
  }

  const createUser = () => {
    const userDto = {
      name: name,
      lastName: lastName,
      email: email,
      phoneNumber: phoneNumber,
      trainingType: typeOfTraining,
      role: typeOfUser
    }
    const token = JSON.parse(localStorage.getItem('user')).token

    fetch(`${urlConfig.trainingUrl}/api/users`, {
      method: 'POST',
      headers: {
        'Content-type': 'application/json',
        'Authorization': `Bearer ${token}`,
      },
      body: JSON.stringify(userDto)
    })
      .then((response) => {
        if (!response.ok) {
          return Promise.reject(response);
        }
        return response.json();
      })
      .then((data) => {
        console.log(data);
      })
      .catch((error) => {
        console.log(error);
      });

  }

  const dropDown = (e) => {
    setTypeOfTraining(e.target.value)
  }

  return (
    <div className={style.bg}>
      <div className={style.profile}>
        <div>
          <div className={style.subject}>
            <h6>Personal Details</h6>
          </div>
          <div>
            <div className={style[nameClass]}>
              <label>Name</label>
              <input className={style.formControl} type="text"
                onChange={nameChangeHandler}
                onBlur={nameBlurHandler}
              />
            </div>
          </div>
          <div>
            <div className={style[lastNameClass]}>
              <label>Last name</label>
              <input className={style.formControl} type="text"
                onChange={lastNameChangeHandler}
                onBlur={lastNameBlurHandler}
              />
            </div>
          </div>
          <div>
            <div>
              <label>Phone</label>
              <input className={style.formControl} type="text"
                onChange={(e) => setPhoneNumber(e.target.value)}
              />
            </div>
          </div>
          <div >
            <div className={style[emailClass]}>
              <label>Email</label>
              <input className={style.formControl} type="email"
                onChange={emailChangeHandler}
                onBlur={emailBlurHandler}
              />
            </div>
          </div>
          <div >
            <div>
              <label>Training type</label>
              <select onChange={dropDown}>
                <option >Select...</option>
                <option value="JOGA">JOGA</option>
                <option value='CROSSFIT'>CROSSFIT</option>
                <option value="FUNCTIONAL">FUNCTIONAL</option>
              </select>
            </div>
          </div>
          <div >
            <div>
              <label>User type</label>
              <select
                onChange={(e) => setTypeOfUser(e.target.value)}
              >
                <option >Select...</option>
                <option value="ROLE_USER">USER</option>
                <option value="ROLE_ADMINISTRATOR">ADMIN</option>
              </select>
            </div>
          </div>
        </div>

        <div>
          <div>
            <div>
              <button className={style.button} type="button" onClick={onSubmit}>Create</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}

export default NewMember;