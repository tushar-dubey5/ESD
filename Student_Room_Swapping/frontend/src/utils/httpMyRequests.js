import axios from 'axios';

// Utility function to fetch swap requests for a specific applicant
export const fetchMyRequests = async (applicantId) => {
    try {
        const response = await axios.get(`http://localhost:8080/api/v1/swap/my-requests?applicantId=${applicantId}`);
        return response.data; // Return the fetched data
    } catch (error) {
        throw new Error('Error fetching requests: ' + error.message); // Throw error for handling in the hook
    }
};
