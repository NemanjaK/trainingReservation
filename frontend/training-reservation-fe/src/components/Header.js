import { NavLink } from 'react-router-dom';
import styles from './Header.module.css';
import { useDispatch } from 'react-redux';
import { authActions } from '../store/auth';
import store from '../store/store';

const Header = (props) => {

    const isAuth = store.getState().isAuthenticated;
    const dispatch = useDispatch();

    console.log(isAuth)
    const LogoutHandler = () => {
        localStorage.clear();
        dispatch(authActions.logout());
        window.location.replace("/home")
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