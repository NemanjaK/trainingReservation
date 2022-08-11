import './Reservation.css';
import * as React from 'react';
import { urlConfig } from '../urlConfig';
import store from '../store/store';
import { useState, useEffect } from 'react';
import Wod from './Wod';
import AddTerm from './AddTerm';
import AdministratorReservation from './AdministratorReservation';


const Term = (props) => {

    const [wodIsShown, setWodIsShown] = useState(false)
    const [addTermIsShown, setAddTermIsShown] = useState(false)
    const [adminReservationShown, setAdminReservationShown] = useState(false)
    
    const showWodHandler = () => {
      setWodIsShown(true);
    }

    const hidWodHandler = () => {
      setWodIsShown(false);
    }

    const showAddTermHandler = () => {
      setAddTermIsShown(true);
    }

    const hideAddTermHandler = () => {
      setAddTermIsShown(false);
    }

    // const showAdminReservationHandler = () => {
    //   setAdminReservationShown(true);
    // }

    // const hideAdminReservationHandler = () => {
    //   setAdminReservationShown(false);
    // }

    const authorization = store.getState().authReducer.role;
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
  
    
      
    const deleteTerm = (id) => {
        const token = JSON.parse(localStorage.getItem('user')).token
        fetch(`${urlConfig.trainingUrl}/api/terms/${id}`, {
          method: 'DELETE',
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
              return response
            }).then((data) => {
              console.log(data)
              window.location.reload()
            })
            .catch((error) => {
              console.log(error);
            });
         
    }  

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
    
    const enebleButtonHandling = (term) => {
      if(term.occupancy == 0 || (expirationDate < todayDate || myDateReservation.includes(term.time.split(' ')[0])) && authorization !== 'ROLE_ADMINISTRATOR'){
        return <a href="#" class="btn btn-primary btn-book-disabled">Reserves</a>
      } else {
        return <a href="#" class="btn btn-primary btn-book" onClick={() => reservationHandling(term.id)}>Reserve</a>
      }
    }
    
    const training = <img src="https://images.pexels.com/photos/841128/pexels-photo-841128.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1" alt="Contemporary Dance"></img>;
    const joga = <img src="https://images.pexels.com/photos/3823039/pexels-photo-3823039.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1" alt="Contemporary Dance"></img>;
   return ( 
    <>
    {props.terms.map((term, index) => (
          <div class="col-md-6">
          {wodIsShown && <Wod description={term.training.description} onHide={hidWodHandler}/>} 
          {addTermIsShown && <AddTerm onHide={hideAddTermHandler}/>}
          {adminReservationShown && <AdministratorReservation />} 
          <div class="timetable-item">
            <div class="timetable-item-img">
            {term.typeOfTraining == 'JOGA' ? joga : training}
            </div>
            <div class="timetable-item-main">
              <div class="timetable-item-time">Start {new Date(term.time).getHours()}H</div>
              <div class="timetable-item-name">Type of training: {term.typeOfTraining}</div>
              <a class="link" onClick={showWodHandler}>Workout of the Day</a>
              {enebleButtonHandling(term)}
              {myDateTimeReservation.includes(term.time) && <a>You have reserved this training term!</a>}
              <div class="timetable-item-like">
                 {authorization === 'ROLE_ADMINISTRATOR' && <button onClick={showAddTermHandler} class="btn btn-primary btn-sm rounded-0" type="button" data-toggle="tooltip" data-placement="top" title="Add"><i class="fa fa-table"></i></button>} 
                 {authorization === 'ROLE_ADMINISTRATOR' && <button onClick={() => deleteTerm(term.id)} class="btn btn-danger btn-sm rounded-0"  type="button" data-toggle="tooltip" data-placement="top" title="Delete"><i class="fa fa-trash"></i></button>}
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