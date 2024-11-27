import React from 'react';
import { Table } from 'react-bootstrap';

// Render table with the swap requests data
const RequestsTable = ({ requests }) => (
    <Table striped bordered hover>
        <thead>
            <tr>
                <th>ID</th>
                <th>Recipient</th>
                <th>Application Message</th>
                <th>Status</th>
                <th>Recipient Message</th>
                <th>Room Number</th>
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
                    <td>{request.roomNumber}</td>
                </tr>
            ))}
        </tbody>
    </Table>
);

export default RequestsTable;
