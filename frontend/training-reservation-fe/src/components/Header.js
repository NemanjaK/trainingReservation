import { NavLink } from 'react-router-dom';
import styles from './Header.module.css';

const Header = (props) => {
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
            <button className={styles.button} onClick={props.onClick}>
                <span>Login</span>
            </button>
            </div>
        </header>
    )
}

export default Header;