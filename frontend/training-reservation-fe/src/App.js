
import { Fragment } from 'react';
import { useState } from 'react';
import { Redirect, Route, Switch } from 'react-router-dom';
import Layout from './components/Layout/Layout';
import Home from './components/Home.js';
import Footer from './components/Layout/Footer';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import Login from './components/Auth/Login';
import Admin from './components/Admin.js';
import Members from './components/Members.js';
import Profile from './components/Profile';
import NewMember from './components/NewMember'
import store from './store/store';


function App() {
  const authorization = store.getState().role;
  const isAuth = store.getState().isAuthenticated;


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
          {isAuth && authorization === 'ROLE_ADMINISTRATOR' && <Route path='/admin' exact>
              <Admin/>
          </Route>}
         <Route path='/members' exact>
              <Members/>
          </Route>
          <Route path='/profile' exact>
              <Profile/>
          </Route>
          <Route path='/newMember' exact>
              <NewMember/>
          </Route>
          <Route path='*'>
            <Redirect to='/' />
          </Route>
        </Switch>      
      <Footer/>
    </Fragment>
  );
}

export default App;
