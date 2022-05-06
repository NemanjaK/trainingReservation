import styles from './Members.module.css';
import * as React from 'react';
import { useState, useEffect } from 'react'
import Pagination from './Pagination';


const Members = () => {

  const [users, setUsers] = useState([])
  const [totalPages, setTotal] = useState() 
  const [page, setPage] = useState(0);

    useEffect(() => {
      const token = JSON.parse(localStorage.getItem('user')).token
      fetch(`http://localhost:8080/api/users?size=10&page=${page}`, {
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
          setUsers(data);
        })
    
  }, [page, setPage])

  return (    
  <div className={styles.background}>
    <table className={styles.table}>
      <thead>
        <tr>
          <th>#</th>
          <th>Name</th>
          <th>Last name</th>
          <th>number</th>
          <th>Type of training</th>
          <th>Expire</th>
        </tr>
      </thead>
      <tbody>
        {users.map(row => (
          <tr>
            <td>{row.id}</td>
            <td>{row.name}</td>
            <td>{row.lastName}</td>
            <td>{row.phoneNumber}</td>
            <td>{row.trainingType}</td>
            <td>{row.membershipExpirationDate}</td>
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
export default Members;

