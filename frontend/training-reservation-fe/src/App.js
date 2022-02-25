
import { Fragment } from 'react';
import { useState } from 'react';
import { Redirect, Route, Switch } from 'react-router-dom';
import Layout from './components/Layout/Layout';
import Home from './components/Home.js';
import Footer from './components/Layout/Footer';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import Login from './components/Auth/Login';


function App() {
  
  const [loginIsShown, setLoginIsShown] = useState(false)

  const showLoginHandler = () => {
    setLoginIsShown(true);
  }

  const hidLoginHandler = () => {
    setLoginIsShown(false);
  }

  return (
    <Fragment>
      {loginIsShown && <Login onHide={hidLoginHandler}/>} 
      <Layout onShowLogin={showLoginHandler}/>
        <Switch>
          <Redirect from='/' to='/home' exact/>
          <Route path='/home' exact>
              <Home/>
          </Route>
        </Switch>      
      <Footer/>
    </Fragment>
  );
}

export default App;