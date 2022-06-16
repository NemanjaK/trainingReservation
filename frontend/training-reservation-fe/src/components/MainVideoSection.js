import React from 'react';
import '../App.css';
import styles from './MainVideoSection.module.css';

const MainVideoSection = () => {
  return (
    <div className={styles['main-container']}>
      <video src='/videos/crossfit-video1.mp4' autoPlay loop muted />
      <h1>What are you waiting for?</h1>
      <p>Join us</p>
      <div className={styles["btns"]}>
        <button className={styles["btn"]}>
          GET STARTED
        </button>
        <button className={styles["btn"]}>
          GALLERY
        </button>
      </div>
    </div>
  );
}

export default MainVideoSection;
