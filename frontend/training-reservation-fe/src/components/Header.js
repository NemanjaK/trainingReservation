import { NavLink } from 'react-router-dom';
import styles from './Header.module.css';
import { useHistory } from "react-router-dom";
import { useSelector, useDispatch } from 'react-redux';
import { authActions } from '../store/auth';


const Header = (props) => {
    const history = useHistory()
    const isAuth = useSelector(state => state.auth.isAuthenticated);
    const dispatch = useDispatch();


    const LogoutHandler = () => {
        localStorage.clear();
        dispatch(authActions.logout());
        history.push("/home")
    }

    let button;
    if(isAuth){
        button = <button className={styles.button} onClick={LogoutHandler}>
        <span>Logout</span>
    </button>;
    } else {
     button = <button className={styles.button} onClick={props.onClick}>
        <span>Login</span>
     </button>;
    }

    return (
        <header className={styles.header}>
            <div className={styles.logo}>
            <a>
                <img src={require("../assets/crossfitlogo.jpg")} alt="logo"/>
            </a>
            </div>
            {/* <h1 className={styles.logo}>CrossFit</h1>          */}
            <nav className={styles.nav}>
                <ul>
                    <li>
                    <NavLink to='/about' >
                            About us
                    </NavLink>
                    </li>
                    <li>
                    <NavLink to='/reservation' >
                            Reservation
                     </NavLink>
                    </li>
                    <li>
                    <NavLink to='/Profile' >
                            Profile
                     </NavLink>
                    </li>
                    <li>
                    <NavLink to='/admin' >
                            Admin
                     </NavLink>
                    </li>
                </ul>
            </nav>
            <div>
            {button}
            </div>
        </header>
    )
}

export default Header;