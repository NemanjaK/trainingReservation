// import { Fragment } from 'react';
// import * as React from 'react';
// import useInput from '../hooks/use-input';
// import style from './AddTerm.module.css'
// import { urlConfig } from '../urlConfig';
// import { useState } from 'react';

// import moment from 'moment';

// const AddTerm = (props) => {

//     const [timeValue, setTimeValue] = useState(new Date());
//     const [occupancy, setOccupacy] = useState()

//     console.log(moment(timeValue).format('YYYY-MM-DD hh:mm'));
//     console.log(occupancy);

//     const stringDate = timeValue;

//     const handleChange = (newValue) => {
//         setTimeValue(newValue);
//     };

//     const occupancyChangeHandler = (newValue) => {
//         setOccupacy(newValue);
//     };

//     const addTerm = () => {
//     const token = JSON.parse(localStorage.getItem('user')).token
//     const TermDTO = { time: moment(timeValue).format('YYYY-MM-DD hh:mm'), occupancy: occupancy}
//     console.log(TermDTO)
//     console.log(JSON.stringify(TermDTO))
//     const requestOptions = {
//       headers: {
//         Accept: "application/json",
//         "Content-Type": "application/json",
//         'Authorization' : `Bearer ${token}`
//       },
//       method: "POST",
//       body: JSON.stringify(TermDTO),
//     };

//     fetch(`${urlConfig.trainingUrl}/api/terms`, requestOptions)
//       .then((response) => {
//         if (!response.ok) {
//           return Promise.reject(response);
//         }
//         return response;
//       })
//       .then((data) => {
//         console.log(data)
//         // window.location.reload()
//       })
//       .catch((error) => {
//         console.log(error);
//       });
//     }

//     return (
//         <Fragment>
//         <div className={style.backdrop} onClick={props.onHide}></div>
//         <div className={style.modal}>
//         <section className={style.login}>
//             <form>
//                <div className={style['form-control']}>
//                    <label htmlFor='email'>Date and time</label>
//                 </div> 
//                 <div>

//                 </div>
//                 <div className={style['form-control']}>
//                    <label htmlFor='email'>Capacity</label>
//                 </div> 
//                 <div className={style['form-control']}>
//                    <input
//                     type='number'
//                     onChange={(e) => occupancyChangeHandler(e.target.value)}
//                     />
//                 </div> 
//                 <button className={style.button} onClick={props.onHide}>
//                     Close
//                 </button>
//                 <button onClick={addTerm} className={style.button}>
//                     Add
//                 </button>
//             </form>      
//         </section>
//         </div>
//         </Fragment>
//     )

// }

// export default AddTerm; 