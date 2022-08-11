import React from 'react';
import { NavLink } from 'react-router-dom';
import '../App.css';
import styles from './MainVideoSection.module.css';
import store from '../store/store';
import MyReservations from './MyReservations';

const MainVideoSection = () => {

  const authorization = store.getState().authReducer.role;

  return (
    <div className={styles['main-container']}>
      <video src='/videos/crossfit-video1.mp4' autoPlay loop muted />
      <h1>What are you waiting for?</h1>
      <p>Join us</p>
      <div className={styles["btns"]}>
        <button className={styles["btn"]}>
        <NavLink to='/reservation' className='nav-links'>
        {authorization === 'ROLE_ADMINISTRATOR' ? 'Manage reservations and terms' : 'Book your training'}
         </NavLink>
        </button>
        <button className={styles["btn"]}>
        <NavLink to='/myReservations' className='nav-links'>
         {authorization === 'ROLE_ADMINISTRATOR' ? 'Reservations' : 'My Reservations'}
         </NavLink>
        </button>
      </div>
    </div>
  );
}

export default MainVideoSection;
