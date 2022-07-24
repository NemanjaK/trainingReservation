import React from "react";

const Pagination = (props) => {

  const totalPages = props.pageNumbers;
  const pageNumbers = []
  for(let i = 0; i <= totalPages - 1; i++){
    pageNumbers.push(i);
  }
  const paginateHandler = (num) => {
      props.changePage(num)
  }
  return (
    <nav>
    <ul className='pagination'>
      {pageNumbers.map(number => (
        <li key={number} className='page-item'>
          <a onClick={() => paginateHandler(number)} className='page-link'>
            {number}
          </a>
        </li>
      ))}
    </ul>
  </nav>
  )

}
export default Pagination;