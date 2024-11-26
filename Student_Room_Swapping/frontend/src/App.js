import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import ReceivedSwappingRequests from './Components/ReceivedSwappingRequests.js';
import 'bootstrap/dist/css/bootstrap.min.css';
import Contact from './Pages/Contact.js';
import LoginPage from './Components/Auth/LoginPage.js'; // Adjust path if needed
import LandingPage from './Pages/LandingPage.js';
import RequestSwapping from './Components/RequestSwapping.js';
import MyRequests from './Components/MyRequests.js';
import AboutMe from './Components/AboutMe.js';
const App = () => {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<LoginPage/>} />
                <Route path="/landing" element={<LandingPage/>} />
                <Route path="/request-swapping" element={< RequestSwapping/>} />
                 <Route path="/received-swapping-requests" element={<ReceivedSwappingRequests />} />   
                 <Route path='/my-requests' element = {<MyRequests/>}/>
                 <Route path="/about-me" element={<AboutMe />} />
                 <Route path='/contact' element={<Contact/>}/>
            </Routes>
        </Router>
    );
};

export default App;
