import './Reservation.css';
import * as React from 'react';
import { urlConfig } from '../urlConfig';
import store from '../store/store';
import { useDispatch } from 'react-redux';
import { reservActions } from '../store/reservation';
import { useState, useEffect } from 'react';

const Term = (props) => {

    const expirationDate = store.getState().authReducer.expirationDate;
    const todayDate = new Date().toISOString().split('T')[0];
    const [myDateReservation, setMyDateResevation] = useState([]);
    const [myDateTimeReservation, setMyDateTimeResevation] = useState([]);

    useEffect(() => {
      const token = JSON.parse(localStorage.getItem('user')).token
      fetch(`${urlConfig.trainingUrl}/api/reservations/myReservation`, {
        headers:{
            'Authorization' : `Bearer ${token}`,
          } 
        })
            .then((response) => {
              return response.json();
            }).then((data) => {
              data.forEach(element => {
                setMyDateTimeResevation((myDateTimeReservation) => [...myDateTimeReservation, element.term.time])
                setMyDateResevation((myDateReservation) => [...myDateReservation, element.term.time.split(' ')[0]])
              });

            })
      }, [])
  

    const reservationHandling = (id) => {
        const token = JSON.parse(localStorage.getItem('user')).token
        fetch(`${urlConfig.trainingUrl}/api/reservations/${id}`, {
          method: 'POST',
          headers: {
              'Content-type': 'application/json',
              'Authorization' : `Bearer ${token}`,
            },
            })
            .then((response) => {
              if (!response.ok) {
                console.log('a')
                return Promise.reject(response);
              }
              console.log(response)
              return response.json();
            }).then((data) => {
              console.log(data)
              window.location.reload()
            })
            .catch((error) => {
              console.log(error);
            });
         
    }
    
    const training = <img src="https://images.pexels.com/photos/841128/pexels-photo-841128.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1" alt="Contemporary Dance"></img>;
    const joga = <img src="https://images.pexels.com/photos/3823039/pexels-photo-3823039.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1" alt="Contemporary Dance"></img>;
   return ( 
    <>
    {props.terms.map((term, index) => (
        <div class="col-md-6">
        <div class="timetable-item">
          <div class="timetable-item-img">
          {term.training.typeOfTraining == 'JOGA' ? joga : training}
          </div>
          <div class="timetable-item-main">
            <div class="timetable-item-time">Start {new Date(term.time).getHours()}H</div>
            <div class="timetable-item-name">Type of training: {term.training.typeOfTraining}</div>
            <a href="#" class={term.occupancy == 0 || expirationDate < todayDate || myDateTimeReservation.includes(term.time.split(' ')[0])  ? "btn btn-primary btn-book-disabled" : "btn btn-primary btn-book"} onClick={() => reservationHandling(term.id)}>Reserve</a>
            {myDateTimeReservation.includes(term.time) && <a>You have reserved this training term!</a>}
            <div class="timetable-item-like">
              <i class="fa fa-heart-o" aria-hidden="true"></i>
              <i class="fa fa-heart" aria-hidden="true"></i>
              <div class={term.occupancy <= 1 ? "timetable-item-like-count-red" : "timetable-item-like-count"}>{term.occupancy} left</div>
            </div>
          </div>
        </div>
      </div>
    ))}
    </>
    )

}

export default Term;