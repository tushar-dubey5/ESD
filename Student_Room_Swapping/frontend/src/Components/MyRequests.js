import React from 'react';
import Navbar from '../Components/Navbar';
import RequestsTable from './RequestsTable';
import useMyRequests from '../hooks/useMyRequests';
const MyRequests = () => {
    const { requests, isLoading, error } = useMyRequests(); // Use custom hook for data fetching

    if (error) {
        return <div>Error: {error}</div>;
    }

    return (
        <>
            <Navbar />
            <div className="container mt-4">
                <h2>My Room Swapping Requests</h2>
                <RequestsTable requests={requests} /> {/* Pass data to presentation component */}
            </div>
        </>
    );
};

export default MyRequests;
