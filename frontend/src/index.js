import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import {createStore, applyMiddleware, compose} from "redux";
import {Provider} from "react-redux";
import reducer, {getThreads} from './modules/reducer'
const handleAsync = function(reduxApi){
    return (reducer)=>{
        return (action)=>{
           if(typeof action == 'function'){
              return action(reduxApi.dispatch, reduxApi.getState)
           }

           reducer(action);
        }
    }
}
const composeEnhancers = (typeof window !== 'undefined' && window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__) || compose;
const store = createStore(reducer, composeEnhancers(applyMiddleware(handleAsync)) );
store.dispatch(getThreads())

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
      <Provider store={store}>
          <App />
      </Provider>
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
