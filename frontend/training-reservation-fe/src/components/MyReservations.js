import styles from './MyReservation.module.css';
import * as React from 'react';
import { useState, useEffect } from 'react'
import Pagination from './Pagination';
import store from '../store/store';
import { urlConfig } from '../urlConfig';
import SearchIcon from "@material-ui/icons/Search";
import "./SearchBar.css";

const MyReservations = () => {

  const [totalPages, setTotal] = useState() 
  const [page, setPage] = useState(0);
  const [myReservations, setMyReservations] = useState([])
  const [allMyResevations, setAllMyReservations] = useState([]);
  const authorization = store.getState().authReducer.role;
  const isAuth = store.getState().authReducer.isAuthenticated;

  const handleFilter = (event) => {
    const searchWord = event.target.value;
    const newFilter = myReservations.filter((value) => {
      return value.user.name.toLowerCase().includes(searchWord.toLowerCase());
    });

    setMyReservations(newFilter)
    if (searchWord === "") {
      console.log('PRAZAN')
      setMyReservations(allMyResevations);
    } else {
      searchWord(newFilter);
    }
  };

    useEffect(() => {
      const token = JSON.parse(localStorage.getItem('user')).token
      if (authorization === 'ROLE_ADMINISTRATOR'){
        fetch(`${urlConfig.trainingUrl}/api/reservations?size=15&page=${page}`, {
          headers:{
              'Authorization' : `Bearer ${token}`,
          } 
      })
          .then((response) => {
            const header = response.headers.get('total');
            setTotal(header)
            return response.json();
          }).then((data) => {
            console.log(data)
            setMyReservations(data);
            setAllMyReservations(data);
          })
      } else if (authorization === 'ROLE_USER'){
          fetch(`${urlConfig.trainingUrl}/api/reservations/myReservation`, {
            headers:{
                'Authorization' : `Bearer ${token}`,
            } 
        })
            .then((response) => {
              const header = response.headers.get('total');
              setTotal(header)
              return response.json();
            }).then((data) => {
              console.log(data + 'MY RESERVATIONS')
              setMyReservations(data);
              setAllMyReservations(data);
            })
      }    
  }, [page, setPage])

  
  const deleteReservation = (id) => {
    const token = JSON.parse(localStorage.getItem('user')).token
    fetch(`${urlConfig.trainingUrl}/api/reservations/${id}`, {
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
          return response;
        }).then((data) => {
          console.log(data)
          window.location.reload()
        })
        .catch((error) => {
          console.log(error);
        });
      }


  const button = (time, id) => {
    const date = new Date(time);
    date.setHours(date.getHours() - 1);

    const buttonCant = <button> CANT CANCEL</button>

    const dateNow = new Date();
    if (date > dateNow || authorization === 'ROLE_ADMINISTRATOR'){
      const buttonCancel = <button  onClick={() => deleteReservation(id)}>CANCEL</button>
      return buttonCancel
    }

    return buttonCant
  }
  
  return (    
  <div className={styles.background}>
      <div className="search">
           <div className="searchInputs">
            <input type="text" onChange={handleFilter}/>
            <div className="searchIcon"> 
                    <SearchIcon/>
            </div> 
          </div>
      </div>    
    <table className={styles.table}>
      <thead>
        <tr>
          <th>Date</th>
          <th>Time</th>
          {authorization === 'ROLE_ADMINISTRATOR' && <th>Name</th>}
          {authorization === 'ROLE_ADMINISTRATOR' && <th>Last name</th>}
          {authorization === 'ROLE_ADMINISTRATOR' && <th>Email</th>}
          <th>Cancel</th>
        </tr>
      </thead>
      <tbody>
        {myReservations.map(row => (
          <tr>
            <td>{row.term.time.split(' ')[0].toString()}</td>
            <td>{row.term.time.split(' ')[1]}</td>
            {authorization === 'ROLE_ADMINISTRATOR' && <td>{row.user.name}</td>}
            {authorization === 'ROLE_ADMINISTRATOR' && <td>{row.user.lastName}</td>}
            {authorization === 'ROLE_ADMINISTRATOR' && <td>{row.user.email}</td>}
           {button(row.term.time, row.id)}
          </tr>
        ))}
        
      </tbody>
      <div className={styles.pagination}>
      <Pagination 
        pageNumbers={totalPages}
        changePage={setPage}
      />
       </div>
    </table>
  </div>
);
}
export default MyReservations;

