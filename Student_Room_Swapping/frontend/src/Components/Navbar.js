import React from "react";
import { Link } from "react-router-dom";
import Home from "../Pages/Home";

const Navbar = () => {
  return (
    <nav className="navbar navbar-expand-lg navbar-light bg-light">
      <div className="container-fluid">
        <Link className="navbar-brand" to="/landing">Room Swapping App</Link>
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
          </ul>
        </div>
      </div>
    </nav>
  );
};

export default Navbar;
