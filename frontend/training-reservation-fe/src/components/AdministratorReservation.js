import { Fragment } from 'react';
import * as React from 'react';
import useInput from '../hooks/use-input';
import style from './AddTerm.module.css'
import { urlConfig } from '../urlConfig';
import { useState, useEffect } from 'react';

import moment from 'moment';
import { Search } from '@material-ui/icons';
import SearchBar from './SearchBar';

const AddTerm = (props) => {

    const [timeValue, setTimeValue] = useState(new Date());
    const [occupancy, setOccupacy] = useState();
    const [userData, setUserData] = useState();
    
    console.log(moment(timeValue).format('YYYY-MM-DD hh:mm'));
    console.log(occupancy);

    const stringDate = timeValue;

    const handleChange = (newValue) => {
        setTimeValue(newValue);
    };

    const occupancyChangeHandler = (newValue) => {
        setOccupacy(newValue);
    };

    useEffect(() => {
    const token = JSON.parse(localStorage.getItem('user')).token
    fetch(`${urlConfig.trainingUrl}/api/users`, {
      headers:{
          'Authorization' : `Bearer ${token}`,
        } 
      })
          .then((response) => {
            return response.json();
          }).then((data) => {
            console.log(data)
            setUserData(data)
          })
    }, [])

    const addTerm = () => {
    const token = JSON.parse(localStorage.getItem('user')).token
    const TermDTO = { time: moment(timeValue).format('YYYY-MM-DD hh:mm'), occupancy: occupancy}
    console.log(TermDTO)
    console.log(JSON.stringify(TermDTO))
    const requestOptions = {
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
        'Authorization' : `Bearer ${token}`
      },
      method: "POST",
      body: JSON.stringify(TermDTO),
    };

    fetch(`${urlConfig.trainingUrl}/api/terms`, requestOptions)
      .then((response) => {
        if (!response.ok) {
          return Promise.reject(response);
        }
        return response;
      })
      .then((data) => {
        console.log(data)
        // window.location.reload()
      })
      .catch((error) => {
        console.log(error);
      });
    }

    return (
        <Fragment>
        <div className={style.backdrop} onClick={props.onHide}></div>
        <div className={style.modal}>
        <section className={style.login}>
            <form>  
                <div className={style['form-control']}>
                <SearchBar reserve={props.reservation} placeholder="Enter a Book Name..."/>
                </div>
                <div className={style['form-control']}>
                   <label htmlFor='email'>Capacity</label>
                </div> 
                <div className={style['form-control']}>
                   <input
                    type='number'
                    onChange={(e) => occupancyChangeHandler(e.target.value)}
                    />
                </div> 
                <button className={style.button} onClick={props.onHide}>
                    Close
                </button>
                <button onClick={addTerm} className={style.button}>
                    Add
                </button>
            </form>      
        </section>
        </div>
        </Fragment>
    )

}

export default AddTerm; 