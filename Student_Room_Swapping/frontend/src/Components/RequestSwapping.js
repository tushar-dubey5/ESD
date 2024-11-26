// src/components/RequestSwapping.js
import React from "react";

import useSwapRequest from "../hooks/useSwapRequest";
import Navbar from "./Navbar";
import { useNavigate } from "react-router-dom";


const RequestSwapping = () => {
    const { formData, isLoggedIn, handleChange, handleSubmit } = useSwapRequest();
    const navigate = useNavigate()
    if(!isLoggedIn){
        navigate('/')
    }// Prevent rendering if not logged in

    return (
        <>
            <Navbar />
            <div>
                <h2>Create Room Swapping Request</h2>
                <form onSubmit={handleSubmit} className="request">
                    <div className="mb-3">
                        <label htmlFor="applicantId" className="form-label">Applicant</label>
                        <input
                            type="text"
                            className="form-control"
                            id="applicantId"
                            name="applicantId"
                            value={formData.applicantId}
                            readOnly
                        />
                    </div>
                    <div className="mb-3">
                        <label htmlFor="recipientId" className="form-label">Recipient</label>
                        <input
                            type="text"
                            className="form-control"
                            id="recipientId"
                            name="recipientId"
                            value={formData.recipientId}
                            onChange={handleChange}
                            required
                        />
                    </div>
                    <div className="mb-3">
                        <label htmlFor="applicationMessage" className="form-label">Message</label>
                        <textarea
                            className="form-control"
                            id="applicationMessage"
                            name="applicationMessage"
                            value={formData.applicationMessage}
                            onChange={handleChange}
                            required
                        />
                    </div>
                    <button type="submit" className="btn btn-primary">Submit</button>
                </form>
            </div>
        </>
    );
};

export default RequestSwapping;
