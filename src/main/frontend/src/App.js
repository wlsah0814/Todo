import './App.css';
import React from "react";
import {Login} from "./components/login/Login";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import {Register} from "./components/login/Register";

export const App = () => {
  return (
      <BrowserRouter>
          <Routes>
              <Route path={'/'} element={<Login />}/>
              <Route path={'/register'} element={<Register />}/>
          </Routes>
      </BrowserRouter>
  );
}
