import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import LoginPage from './Pages/LoginPage.js';
import LandingPage from './Pages/LandingPage.js';
import Home from './Pages/Home.js';
import ReceivedSwappingRequests from './Pages/ReceivedSwappingRequests.js';
import RequestSwapping from './Pages/RequestSwapping.js';


const App = () => {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<LoginPage />} />
                <Route path="/landing" element={<LandingPage />} />
                <Route path="/home" element={<Home />} />
                <Route path="/request-swapping" element={<RequestSwapping />} />
                 <Route path="/received-swapping-requests" element={<ReceivedSwappingRequests />} />   
            </Routes>
        </Router>
    );
};

export default App;
