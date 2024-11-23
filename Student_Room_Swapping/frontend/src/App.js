import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import LoginPage from './Pages/LoginPage.js';
import LandingPage from './Pages/LandingPage.js';
import Home from './Pages/Home.js';
import ReceivedSwappingRequests from './Pages/ReceivedSwappingRequests.js';
import RequestSwapping from './Pages/RequestSwapping.js';
import MyRequests from './Pages/MyRequests.js';
import 'bootstrap/dist/css/bootstrap.min.css';
import AboutMe from './Pages/AboutMe.js';



const App = () => {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<LoginPage />} />
                <Route path="/landing" element={<LandingPage />} />
                <Route path="/request-swapping" element={<RequestSwapping />} />
                 <Route path="/received-swapping-requests" element={<ReceivedSwappingRequests />} />   
                 <Route path='/my-requests' element = {<MyRequests/>}/>
                 <Route path="/about-me" element={<AboutMe />} />
            </Routes>
        </Router>
    );
};

export default App;
