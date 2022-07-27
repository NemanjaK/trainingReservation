import React, { Fragment } from "react";
import Header from "../Header";


const Layout = (props) => {
    
    return (
        <Fragment>
            <Header onClick={props.onShowLogin}/>
            <main >{props.children}</main>
        </Fragment>
    )
}

export default Layout;