import './App.css';
import {Routes, Route} from 'react-router-dom';
import 'primeflex/primeflex.css';
import 'primereact/resources/primereact.min.css';
import 'primeicons/primeicons.css'; 
import 'primereact/resources/themes/md-light-deeppurple/theme.css';
import Navbar from './Components/NavBar/Navbar';
 import { useEffect, useState } from 'react';
import { Login } from './Components/Login/Login';
import { Register } from './Components/Register/Register';

function App() {
  const [accessToken,setAccessToken]=useState();
  const [refreshToken,setRefreshToken]=useState();
  const [user,setUser]=useState();

  const getTokens=(accessToken,refreshToken,user)=>{
    setAccessToken(accessToken);
    setRefreshToken(refreshToken);
    setUser(user);
    console.log(user);
    localStorage.setItem('authenticated', JSON.stringify(true))
    localStorage.setItem('user', JSON.stringify(user))
    console.log(localStorage.getItem("user"));
    console.log(accessToken,refreshToken)
  }
  return (
    <div className="App">
      <Navbar user={user}/>
      <Routes>
        <Route path='registration' element={<Register/>} />
        <Route path='/login' element={<Login Onlogin={getTokens} />} />
      </Routes>
    </div>
  );
}

export default App;
