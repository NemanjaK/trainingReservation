import React, { Fragment } from "react";
import Header from "../Header";
import NavBar from "../NavBar/Index";


const Layout = (props) => {
    
    return (
        <Fragment>
            {/* <NavBar/> */}
            <Header onClick={props.onShowLogin}/>
            <main >{props.children}</main>
        </Fragment>
    )
}

export default Layout;