import React from "react";
import Navbar from "../Components/Navbar.js";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Home from "./Home";
import RequestSwapping from "./RequestSwapping";
import ReceivedSwappingRequests from "./ReceivedSwappingRequests";
import '../App.css'

const LandingPage = () => {
  return (
   <>
      <Navbar />
      <div class="landing-page">
      <div class="overlay">
      <h1>Welcome to IIITB Room Swapping Portal</h1>
    
      </div>
      </div>
        <footer  className="footer">
        <p>Developed By Tushar Dubey</p>
      </footer>
      
  
    </>
  );
};

export default LandingPage;
