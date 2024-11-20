import React from "react";
import Navbar from "../Components/Navbar.js";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Home from "./Home";
import RequestSwapping from "./RequestSwapping";
import ReceivedSwappingRequests from "./ReceivedSwappingRequests";

const LandingPage = () => {
  return (
   
      <Navbar />
  );
};

export default LandingPage;
