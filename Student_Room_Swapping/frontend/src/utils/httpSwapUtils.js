// src/utils/httpSwapUtils.js
import axios from "axios";

// Utility function to create a swap request
export const createSwapRequest = async (formData, token) => {
    try {
        const response = await axios.post(
            "http://localhost:8080/api/v1/swap/request",
            formData,
            {
                headers: { Authorization: `Bearer ${token}` }, // Include JWT token in headers
            }
        );
        return response.data; // Return the response data
    } catch (error) {
        throw new Error(error.response?.data?.message || "Request failed");
    }
};
