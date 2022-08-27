import './Reservation.css';
import * as React from 'react';
import { useState, useEffect } from 'react'
import { urlConfig } from '../urlConfig';
import Term from './Term';
import store from '../store/store';
import moment from 'moment';


const Reservation = (props) => {

  const expirationDate = store.getState().expirationDate;
  const today = new Date().toISOString().split('T')[0];
  const todayDate = new Date();

  const [terms, setTerms] = useState([]);
  const [day, setDay] = useState(todayDate.toISOString())
  const [active, setActive] = useState(todayDate.getDay());

  useEffect(() => {
    const formatToday = moment(day).format('YYYY-MM-DD')
    const token = JSON.parse(localStorage.getItem('user')).token
    fetch(`${urlConfig.trainingUrl}/api/terms/day/${formatToday}`, {
      headers: {
        'Authorization': `Bearer ${token}`,
      }
    })
      .then((response) => {
        return response.json();
      }).then((data) => {
        console.log(data)
        setTerms(data);
      })
  }, [day, setDay])

  const dayHandling = (number) => {

    const today = new Date();
    const first = today.getDate() - today.getDay() + 1;
    const sundayCheck = today.getDay();

    if (sundayCheck == 0 && number == 7) {
      setDay(todayDate.toISOString().split('T')[0])
      setActive(todayDate.getDay())
      return;
    }

    switch (number) {
      case 1:
        const mondays = new Date(today.setDate(first)).toISOString();
        const momentsMonday = moment(mondays).format('YYYY-MM-DD')
        setDay(momentsMonday);
        break;
      case 2:
        const second = first + 1;
        const tuesday = new Date(today.setDate(second)).toISOString();
        const momentsTuesday = moment(tuesday).format('YYYY-MM-DD')
        setDay(momentsTuesday);
        break;
      case 3:
        const third = first + 2;
        const wendsday = new Date(today.setDate(third)).toISOString();
        const momentsWendsday = moment(wendsday).format('YYYY-MM-DD')
        setDay(momentsWendsday);
        break;
      case 4:
        const fourth = first + 3;
        const thuersday = new Date(today.setDate(fourth)).toISOString();
        const momentsThuersday = moment(thuersday).format('YYYY-MM-DD')
        setDay(momentsThuersday);
        break;
      case 5:
        const fifth = first + 4;
        const friday = new Date(today.setDate(fifth)).toISOString();
        const momentsFriday = moment(friday).format('YYYY-MM-DD')
        setDay(momentsFriday);
        break;
      case 6:
        const sixth = first + 5;
        const saturday = new Date(today.setDate(sixth)).toISOString();
        const momentsSaturday = moment(saturday).format('YYYY-MM-DD')
        setDay(momentsSaturday);
        break;
      case 7:
        const seventh = first + 6;
        const sunday = new Date(today.setDate(seventh)).toISOString();
        const momentsSunday = moment(sunday).format('YYYY-MM-DD')
        setDay(momentsSunday);
        break;
    }
  }

  const activeButton = (num) => {
    setActive(num)
  }
  return (
    <div class="idance">
      <div class="schedule content-block">
        <div class="container">
          <h2 data-aos="zoom-in-up" class="aos-init aos-animate">Schedule </h2>
          {expirationDate < today && <h2 class="expire">Your subscription has expired</h2>}
          <div class="timetable">

            <nav class="nav nav-tabs">
              <a class={active === 1 ? "nav-link active" : "nav-link"} onClick={() => { activeButton(1); dayHandling(1) }}>Mon</a>
              <a class={active === 2 ? "nav-link active" : "nav-link"} onClick={() => { activeButton(2); dayHandling(2) }}>Tue</a>
              <a class={active === 3 ? "nav-link active" : "nav-link"} onClick={() => { activeButton(3); dayHandling(3) }}>Wed</a>
              <a class={active === 4 ? "nav-link active" : "nav-link"} onClick={() => { activeButton(4); dayHandling(4) }}>Thu</a>
              <a class={active === 5 ? "nav-link active" : "nav-link"} onClick={() => { activeButton(5); dayHandling(5) }}>Fri</a>
              <a class={active === 6 ? "nav-link active" : "nav-link"} onClick={() => { activeButton(6); dayHandling(6) }}>Sat</a>
              <a class={active === 0 ? "nav-link active" : "nav-link"} onClick={() => { activeButton(7); dayHandling(7) }}>Sun</a>
            </nav>

            <div class="tab-content">
              <div class="tab-pane show active">
                <div class="row">
                  <Term terms={terms} />
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );

}

export default Reservation;
