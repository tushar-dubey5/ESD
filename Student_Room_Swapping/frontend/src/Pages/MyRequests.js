import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Table } from 'react-bootstrap';
import Navbar from '../Components/Navbar';
import { useNavigate } from 'react-router-dom';

const MyRequests = () => {
    const navigate = useNavigate()
    const [requests, setRequests] = useState([]);
    const applicantId = localStorage.getItem("Id"); // Assuming the ID is stored in local storage after login.
   
    console.log("Applicant Id: ",applicantId);
    useEffect(() => {
        if (!applicantId) {
            navigate('/'); // Redirect to the home page if the user is not logged in
            return;
        }
        axios
            .get(`http://localhost:8080/api/v1/swap/my-requests?applicantId=${applicantId}`)
            .then((response) => setRequests(response.data))
            .catch((error) => console.error("Error fetching requests:", error));
    }, [applicantId]);

    return (
        <>
        <Navbar/>
        <div className="container mt-4">
            <h2>My Room Swapping Requests</h2>
            <Table striped bordered hover>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Recipient</th>
                        <th>Application Message</th>
                        <th>Status</th>
                        <th>Recipient Message</th>
                    </tr>
                </thead>
                <tbody>
                    {requests.map((request) => (
                        <tr key={request.id}>
                            <td>{request.id}</td>
                            <td>{request.recipientName}</td>
                            <td>{request.applicationMessage}</td>
                            <td>{request.status}</td>
                            <td>{request.recipientMessage}</td>
                        </tr>
                    ))}
                </tbody>
            </Table>
        </div>
        </>
    );
};

export default MyRequests;
