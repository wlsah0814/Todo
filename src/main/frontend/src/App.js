import './App.css';
import React from "react";
import {Login} from "./components/login/Login";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import {Register} from "./components/login/Register";
import {LoginFail} from "./components/login/LoginFail";
import {Home} from "./components/home/Home";
import {LoginSuccess} from "./components/login/LoginSuccess";

export const App = () => {
  return (
      <BrowserRouter>
          <Routes>
              <Route path={'/'} element={<Login />}/>
              <Route path={'/home'} element={<Home />}/>
              <Route path={'/register'} element={<Register />}/>
              <Route path={'/login/fail'} element={<LoginFail />} />
              <Route path={'/login/success'} element={<LoginSuccess />} />
          </Routes>
      </BrowserRouter>
  );
}
