import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import LoginPage from './Pages/LoginPage.js';
import LandingPage from './Pages/LandingPage.js';


const App = () => {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<LoginPage />} />
                <Route path="/landing" element={<LandingPage />} />
            </Routes>
        </Router>
    );
};

export default App;
