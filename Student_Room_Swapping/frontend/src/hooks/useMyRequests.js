import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { fetchMyRequests } from '../utils/httpMyRequests';
import MyRequest from '../models/MyRequest';
function useMyRequests() {
    const [requests, setRequests] = useState([]);
    const [isLoading, setIsLoading] = useState(true);
    const [error, setError] = useState(null);
    const navigate = useNavigate();
    const applicantId = localStorage.getItem('Id'); // Retrieve applicant ID from localStorage

    useEffect(() => {
        const loadRequests = async () => {
            if (!applicantId) {
                navigate('/'); // Redirect to home page if the user is not logged in
                return;
            }
            setIsLoading(true);
            try {
                const fetchedRequests = await fetchMyRequests(applicantId); // Fetch data from utility function
                const mappedRequests = fetchedRequests.map((data) => new MyRequest(data)); // Map data to model
                setRequests(mappedRequests); // Update state with mapped data
            } catch (err) {
                setError(err.message); // Handle errors
            } finally {
                setIsLoading(false); // Update loading status
            }
        };

        loadRequests();
    }, [applicantId, navigate]);

    return { requests, isLoading, error }; // Return states and data
}

export default useMyRequests;
