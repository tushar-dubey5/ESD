// src/hooks/useSwapRequest.js
import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import SwapRequest from "../models/SwapRequest";
import { createSwapRequest } from "../utils/httpSwapUtils";

function useSwapRequest() {
    const [formData, setFormData] = useState(new SwapRequest({}));
    const [isLoggedIn, setIsLoggedIn] = useState(false);
    const navigate = useNavigate();

    useEffect(() => {
        const userId = localStorage.getItem("Id"); // Fetch user ID from localStorage
        if (!userId) {
            navigate("/"); // Redirect to login if not logged in
            return;
        }
        setIsLoggedIn(true);
        setFormData((prev) => ({ ...prev, applicantId: userId })); // Set applicant ID
    }, [navigate]);

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value }); // Update form data
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const token = localStorage.getItem("token"); // Fetch token from localStorage
            await createSwapRequest(formData, token); // Call API to create swap request
            alert("Swap request created successfully!");
            navigate("/landing"); // Redirect to landing page
        } catch (error) {
            alert("Failed to create swap request: " + error.message);
        }
    };

    return { formData, isLoggedIn, handleChange, handleSubmit };
}

export default useSwapRequest;
