import React, { useState, useEffect } from "react";
import "./SearchBar.css";
import SearchIcon from "@material-ui/icons/Search";
import CloseIcon from "@material-ui/icons/Close";
import { urlConfig } from '../urlConfig';


function SearchBar({reserve,  placeholder}) {

  const [userData, setUserData] = useState([]);
  const [filteredData, setFilteredData] = useState([]);
  const [wordEntered, setWordEntered] = useState("");
  
  console.log(filteredData);


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

    const userHandling = () => {
        reserve(2)
    }

    const handleFilter = (event) => {


        const searchWord = event.target.value;
        // setWordEntered(searchWord);
        const newFilter = userData.filter((value) => {
          return value.name.toLowerCase().includes(searchWord.toLowerCase());
        });
    
        setFilteredData(newFilter)
        if (searchWord === "") {
          setFilteredData([]);
        } else {
          setFilteredData(newFilter);
        }
      };

      const clearInput = () => {
        setFilteredData([]);
        setWordEntered("");
      };

  return (
    <div className="search">
      <div className="searchInputs">
        <input type="text" placeholder={placeholder} onChange={handleFilter} />
        <div className="searchIcon"> 
        {filteredData.length === 0 ? ( 
            <SearchIcon/>
         ) : ( 
            <CloseIcon id="clearBtn" onClick={clearInput}/> 
        )}
        </div>
      </div>
      {filteredData.length != 0 && (
        <div className="dataResult">
            {filteredData.map((value, key) =>{
                return (
                <a className="dataItem" onClick={userHandling}taget="_blank">
                    <a>{value.name}</a>
                </a>
            );
            })}
        </div>
    )}
    </div>
  );
}

export default SearchBar;