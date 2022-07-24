import React from 'react'
import {
  Nav,
  NavLink,
  Bars,
  NavMenu,
  NavBtn,
  NavBtnLink
} from './Elements';

function NavBar() {
  return (
    <>
      <Nav>
        <NavLink to="/">
          <h1></h1>
        </NavLink>
        <Bars/>
        <NavMenu>
        <NavLink to="/about" activeStyle>
            About
        </NavLink>
        <NavLink to="/services" activeStyle>
            Signup
        </NavLink>
        <NavLink to="/contact" activeStyle>
            Contact
        </NavLink>
        <NavLink to="/stest" activeStyle>
            Test
        </NavLink>
        </NavMenu>
        <NavBtn>
          <NavBtnLink to='/signin'></NavBtnLink>
        </NavBtn>
      </Nav>
    </>
  )
}

export default NavBar
