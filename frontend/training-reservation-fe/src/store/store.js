import {createStore, applyMiddleware, combineReducers} from "redux";
import { persistStore, persistReducer, persistCombineReducers } from "redux-persist";
import storage from "redux-persist/es/storage";
import authReducer from './auth';
import reserveReducer  from './reservation';

const persistConfig={
  key: 'main-root',
  blacklist: ['navigation'],
  storage,
}

const allReducers = persistCombineReducers(persistConfig, {
  authReducer, reserveReducer
 })

const store=createStore(allReducers, applyMiddleware());

const Persistor= persistStore(store)

export{Persistor}

export default store;