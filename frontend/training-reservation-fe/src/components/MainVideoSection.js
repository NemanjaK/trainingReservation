import React, { useEffect, useState } from 'react';
import { urlConfig } from '../urlConfig';
import '../App.css';
import styles from './MainVideoSection.module.css';
import Wod from './Wod';
import PaymentModal from './Payment/PaymentModal';

const MainVideoSection = () => {


  const [wodIsShown, setWodIsShown] = useState(false);
  const [wod, setWod] = useState();
  const [paymentModal, setPaymentModal] = useState(false);

  const showWodHandler = () => {
    setWodIsShown(true);
  }

  const hidWodHandler = () => {
    setWodIsShown(false);
  }

  useEffect(() => {
    const today = new Date().toISOString().split('T')[0];
    console.log(today);
    fetch(`${urlConfig.trainingUrl}/api/trainings/datum/${today}`, {
    })
      .then((response) => {
        return response.json();
      }).then((data) => {
        console.log(data)
        setWod(data);
      })
  }, [])


  return (
    <div className={styles['main-container']}>
      {wodIsShown && <Wod description={wod.description} onHide={hidWodHandler} />}
      {paymentModal && <PaymentModal
        setShowPayloadModal={()=> setPaymentModal(false)}
        amount={10}
      />}
      <video src='/videos/crossfit-video1.mp4' autoPlay loop muted />
      <h1>What are you waiting for?</h1>
      <p>Workout of the Day</p>
      <div className={styles["btns"]}>
        <button onClick={showWodHandler} className={styles["btn"]}>
          WOD
        </button>
        <button onClick={() => setPaymentModal(true)} className={styles["btn"]}>
          PAY
        </button>
      </div>
    </div>
  );
}

export default MainVideoSection;
