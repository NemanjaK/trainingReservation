import {createStore, applyMiddleware} from "redux";
import { persistStore, persistReducer } from "redux-persist";
import storage from "redux-persist/es/storage";
import authReducer from './auth';

const persistConfig={
  key: 'main-root',
  storage,
}

const persistedReducer=persistReducer(persistConfig, authReducer)

const store=createStore(persistedReducer, applyMiddleware());

const Persistor= persistStore(store)

export{Persistor}

export default store;