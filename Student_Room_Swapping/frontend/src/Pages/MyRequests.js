import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Table } from 'react-bootstrap';

const MyRequests = () => {
    const [requests, setRequests] = useState([]);
    const applicantId = localStorage.getItem("studentId"); // Assuming the ID is stored in local storage after login.

    useEffect(() => {
        axios
            .get(`http://localhost:8080/api/swap-applications/my-requests?applicantId=${applicantId}`)
            .then((response) => setRequests(response.data))
            .catch((error) => console.error("Error fetching requests:", error));
    }, [applicantId]);

    return (
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
    );
};

export default MyRequests;
