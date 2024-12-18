import React from "react";
import { Link, useNavigate } from "react-router-dom";
import Contact from "../Pages/Contact";
import '../App.css'
import Logo from  './logo.png'
const Navbar = () => {
  const navigate = useNavigate()
  const handleLogout = () => {
    // Clear local storage
    localStorage.clear();

    // Redirect to home page
    navigate('/');
  };
  return (
    <nav className="navbar navbar-expand-lg navbar-light bg-light">
      <div className="container-fluid">
        <Link to ="/landing"><img src = {Logo} alt = "IIITB" className ="logo"/></Link>
        <div className="collapse navbar-collapse">
          <ul className="navbar-nav me-auto mb-2 mb-lg-0">
            <li className="nav-item">
              <Link className="nav-link" to="/landing">Home</Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link" to="/request-swapping">Request Swapping</Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link" to="/received-swapping-requests">Received Swapping Requests</Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link" to="/my-requests">My Requests</Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link" to="/about-me">About Me</Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link" to="/contact">Contact</Link>
            </li>

          </ul>
        </div>
        <div className="logout">
        <button 
                className="btn btn-danger nav-link"
                onClick={handleLogout}
                style={{ border: "none", background: "none" }}
              >
                Logout
              </button>
        </div>
      </div>
    </nav>
  );
};

export default Navbar;
