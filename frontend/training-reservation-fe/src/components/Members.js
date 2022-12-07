import './Members.css';
import * as React from 'react';
import { useState, useEffect } from 'react'
import Pagination from './Pagination';
import SearchIcon from "@material-ui/icons/Search";
 import { urlConfig } from '../urlConfig';

const Members = () => {

    const [users, setUsers] = useState([])
    const [totalPages, setTotal] = useState() 
    const [page, setPage] = useState(0);
    const [allUsers, setAllUsers] = useState([])

        const handleFilter = (event) => {

        const searchWord = event.target.value;
        const newFilter = users.filter((value) => {
          return value.name.toLowerCase().includes(searchWord.toLowerCase());
        });
    
        setUsers(newFilter)
        if (searchWord === "") {
          console.log('EMPTY S')
          setUsers(allUsers);
        } else {
          searchWord(newFilter);
        }
      };

    useEffect(() => {
      const token = JSON.parse(localStorage.getItem('user')).token
      fetch(`${urlConfig.trainingUrl}/api/users?size=10&page=${page}`, {
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
          setAllUsers(data);
        })
    
  }, [page, setPage])


  const handleExtension = (id) => {
    console.log(id);
    const token = JSON.parse(localStorage.getItem('user')).token
    const requestOptions = {
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
        'Authorization' : `Bearer ${token}`
      },
      method: "PUT"
    };

    fetch(`${urlConfig.trainingUrl}/api/users/extension/${id}`, requestOptions)
      .then((response) => {
        if (!response.ok) {
          return Promise.reject(response);
        }
        return response;
      })
      .then((data) => {
        console.log(data)
        window.location.reload()
      })
      .catch((error) => {
        console.log(error);
      });
  }

  return (    
  <div className="background">
    <div className="search">\
    <div className="searchInputs">
      <input type="text" onChange={handleFilter}/>
      <div className="searchIcon"> 
            <SearchIcon/>
      </div> 
    </div>
    </div>
    <table className="table">
      <thead>
        <tr>
          <th>#</th>
          <th>Name</th>
          <th>Last name</th>
          <th>number</th>
          <th>Type of training</th>
          <th>Expire</th>
          <th>Extension</th>
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
            <button onClick={() => handleExtension(row.id)}> extension membership</button>
          </tr>
        ))}
        
      </tbody>
      <div className="pagination">
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

