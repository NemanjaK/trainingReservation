import { Fragment } from 'react';
import * as React from 'react';
import useInput from '../hooks/use-input';
import style from './AddTerm.module.css'
import { urlConfig } from '../urlConfig';
import { useState } from 'react';
import Stack from "@mui/material/Stack";
import TextField from "@mui/material/TextField";
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { LocalizationProvider } from "@mui/x-date-pickers/LocalizationProvider";
import { DateTimePicker } from "@mui/x-date-pickers/DateTimePicker";
import CalendarMonthIcon from "@mui/icons-material/CalendarMonth";
import ArrowLeftIcon from "@mui/icons-material/ArrowLeft";
import ArrowRightIcon from "@mui/icons-material/ArrowRight";
import moment from 'moment';

const AddTerm = (props) => {

    const [timeValue, setTimeValue] = useState(new Date());
    const [occupancy, setOccupacy] = useState();
    const [typeOfTraining, setTypeOfTraining] = useState();
    console.log(typeOfTraining);


    console.log(moment(timeValue).format('YYYY-MM-DD hh:mm'));

    const handleChange = (newValue) => {
        const dateValue = new Date(newValue).toLocaleString();
        setTimeValue(dateValue);
    };

    const occupancyChangeHandler = (newValue) => {
        setOccupacy(newValue);
    };

    const typeOfTrainingChangeHandler = (newValue) => {
      setTypeOfTraining(newValue);
  };

    const addTerm = () => {
    const token = JSON.parse(localStorage.getItem('user')).token
    const TermDTO = { time: moment(timeValue).format('YYYY-MM-DD hh:mm'), occupancy: occupancy, typeOfTraining: typeOfTraining}
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
                   <label htmlFor='email'>Date and time</label>
                </div> 
                <div className={style['form-control']}>
                <LocalizationProvider  dateAdapter={AdapterDayjs}>
                    <Stack spacing={3}>
                    <DateTimePicker
                        value={timeValue}
                        onChange={handleChange}
                        components={{
                        OpenPickerIcon: CalendarMonthIcon,
                        LeftArrowIcon: ArrowLeftIcon,
                        RightArrowIcon: ArrowRightIcon
                        }}
                        renderInput={(params) => <TextField {...params} />}
                    />
                    </Stack>
                </LocalizationProvider>
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
                <div className={style['form-control']}>
                  <label>Type of training</label>
                </div>
                <div>
                  <select onChange={(e) => typeOfTrainingChangeHandler(e.target.value)}  >
                    <option value="1">CROSSFIT</option>
                    <option value="2">FUNCTIONAL</option>
                    <option value="0">JOGA</option>
                  </select>
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