import { useEffect, useState } from 'react';
import style from './Profile.module.css'
import { urlConfig } from '../urlConfig';

const Profile = () => {

  const [user, setUser] = useState("");
  const [name, setName] = useState("");
  const [lastName, setLastName] = useState("");
  const [email, setEmail] = useState("");
  const [phoneNumber, setPhoneNumber] = useState("");
  
  useEffect(() => {
    const token = JSON.parse(localStorage.getItem('user')).token
    fetch(`${urlConfig.trainingUrl}/api/users/profile`, {
      headers:{
          'Authorization' : `Bearer ${token}`,
      }})
    .then((response) => {
      return response.json();
    }).then((data) => {
      console.log(data)
      setUser(data);
      setName(data.name);
      setLastName(data.lastName);
      setPhoneNumber(data.phoneNumber);
      setEmail(data.email);
    })
  },[])

  const onSubmit = () => {
    const userDto = {
      user: user,
      name: name,
      lastName: lastName,
      email: email,
      phoneNumber: phoneNumber
    }
    const token = JSON.parse(localStorage.getItem('user')).token
    
    fetch(`${urlConfig.trainingUrl}/api/users/${user.id}`,{
      method: 'PUT',
      headers: {
          'Content-type': 'application/json',
          'Authorization' : `Bearer ${token}`,
      },
      body: JSON.stringify(userDto)
      })
      .then((response) => {
        if (!response.ok) {
          return Promise.reject(response);
        }
        return response.json();
      })
      .then((data) => {
        console.log(data);
      })
      .catch((error) => {
        console.log(error);
      });
  }

  return (
    <div className={style.bg}>
        <div className={style.profile}>
          <div>
            <div className={style.subject}>
              <h6>Personal Details</h6>
            </div>
            <div>
              <div>
                <label>Name</label>
                <input className={style.formControl} type="text" 
                  value={name}
                  readOnly
                />
              </div>
            </div>
            <div>
              <div>
                <label>Last name</label>
                <input className={style.formControl} type="text" 
                  value={lastName}
                  readOnly                />
              </div>
            </div>
            <div>
              <div>
                <label>Phone</label>
                <input className={style.formControl} type="text" 
                  value={phoneNumber}
                  onChange={(e) => setPhoneNumber(e.target.value)}
                />
              </div>
            </div>
            <div >
              <div>
                <label>email</label>
                <input className={style.formControl} type="email"
                  value={email}
                  onChange={(e) => setEmail(e.target.value)}                 />
              </div>
            </div>
            <div >
              <div>
                <label>Expiration date</label>
                <input className={style.formControl} type="url" placeholder={user.membershipExpirationDate} readOnly/>
              </div>
            </div>
          </div>
          
          <div>
            <div>
              <div>
                <button className={style.button} type="button" onClick={onSubmit}>Update</button>
              </div>
            </div>
          </div>
        </div>
      </div>
  )
}

export default Profile;