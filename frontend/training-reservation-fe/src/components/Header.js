import { NavLink } from 'react-router-dom';
import { useDispatch } from 'react-redux';
import { authActions } from '../store/auth';
import store from '../store/store';
import { useEffect, useState } from 'react';
import Dropdown from './Dropdown';
import './Header.css'
import { Button } from 'bootstrap';

const Header = (props) => {

    const isAuth = store.getState().authReducer.isAuthenticated;
    const authorization = store.getState().authReducer.role;
    const [button, setButton] = useState(true);
    const [click, setClick] = useState(false);
    
    const handleClick = () => setClick(!click);
    const closeMobileMenu = () => setClick(false);

    const dispatch = useDispatch();

    const showButton = () => {
        if (window.innerWidth <= 860) {
          setButton(false);
        } else {
          setButton(true);
        }
      };
    
      useEffect(() => {
        showButton();
      }, []);


    window.addEventListener('resize', showButton);

    console.log(isAuth)
    const LogoutHandler = () => {
        localStorage.clear();
        dispatch(authActions.logout());
        window.location.replace("/home")
    }

    let signButton;
    if(isAuth){
        signButton = <button className='logButton' onClick={LogoutHandler}>
        <span>Logout</span>
    </button>;
    } else {
        signButton = <button className='logButton' onClick={props.onClick}>
        <span>SIGN UP</span>
     </button>;
    }

    return (
        <>
          <nav className='navbar'>
              <NavLink to='/' className='navbar-logo' onClick={closeMobileMenu}>
                  CROSSFIT
                  <i class='fab fa-firstdraft' />
              </NavLink>
              <div className='menu-icon' onClick={handleClick}>
                <i className={click ? 'fas fa-times' : 'fas fa-bars'} />
              </div>
                <ul className={click ? 'nav-menu active' : 'nav-menu'}>
                    <li className='nav-item'>
                    <NavLink to='/' className='nav-links' onClick={closeMobileMenu}>
                            Home
                    </NavLink>
                    </li>

                    <li className='nav-item'
                    >
                    <NavLink to='/reservation' className='nav-links' onClick={closeMobileMenu}>
                            Reservation
                     </NavLink>
                    </li>
                    {isAuth && (authorization === 'ROLE_ADMINISTRATOR' || authorization === 'ROLE_USER') && 
                    <li className='nav-item'>
                    <NavLink to='/Profile' className='nav-links' onClick={closeMobileMenu}>
                            Profile
                     </NavLink>
                    </li>}
                    {isAuth && authorization === 'ROLE_ADMINISTRATOR' && 
                    <li className='nav-item'>
                    <NavLink to='/Profile' className='nav-links' onClick={closeMobileMenu}>
                            Admin
                     </NavLink>
                    </li>}

                    <li className='nav-item'>
                    <NavLink to='/' className='nav-links-mobile' onClick={closeMobileMenu}>
                            Sign Up
                     </NavLink>
                    </li>
                </ul>
                {signButton}
            </nav>

        </>
    )
}

export default Header;