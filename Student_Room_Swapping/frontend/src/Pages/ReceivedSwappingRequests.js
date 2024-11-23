import React, { useEffect, useState } from "react";
import axios from "axios";
import Navbar from "../Components/Navbar";
import { useNavigate } from "react-router-dom";

const ReceivedSwappingRequests = () => {
  const [requests, setRequests] = useState([]);
  const [loading, setLoading] = useState(true);
  const [isLoggedIn, setIsLoggedIn] = useState(false); // New state for login status
 const navigate = useNavigate();
  useEffect(() => {
    const token = localStorage.getItem("token");
    const recipientId = localStorage.getItem("Id");

    if (!token || !recipientId) {
      setIsLoggedIn(false); // User is not logged in
      setLoading(false);
      return;
    }

    setIsLoggedIn(true); // User is logged in

    const fetchRequests = async () => {
      try {
        const response = await axios.get(
          `http://localhost:8080/api/v1/swap/recipient/${recipientId}`,
          { headers: { Authorization: `Bearer ${token}` } }
        );
        setRequests(response.data);
        setLoading(false);
      } catch (error) {
        console.error("Failed to fetch requests:", error);
        setLoading(false);
      }
    };
    fetchRequests();
  }, []);

  const handleAccept = async (requestId) => {
    const recipientMessage = prompt("Enter a message for the applicant:");
    if (!recipientMessage) return;

    const token = localStorage.getItem("token");
    try {
      await axios.post(
        `http://localhost:8080/api/v1/swap/accept/${requestId}`,
        recipientMessage,
        { headers: { Authorization: `Bearer ${token}` } }
      );
      alert("Request accepted!");
      setRequests((prev) =>
        prev.map((req) =>
          req.id === requestId ? { ...req, status: "ACCEPTED" } : req
        )
      );
    } catch (error) {
      alert("Failed to accept request: " + error.response?.data?.message);
    }
  };

  const handleReject = async (requestId) => {
    const recipientMessage = prompt("Enter a message for the applicant:");
    if (!recipientMessage) return;

    const token = localStorage.getItem("token");
    try {
      await axios.post(
        `http://localhost:8080/api/v1/swap/reject/${requestId}?recipientMessage=${encodeURIComponent(
          recipientMessage
        )}`,
        null,
        { headers: { Authorization: `Bearer ${token}` } }
      );
      alert("Request rejected!");
      setRequests((prev) =>
        prev.map((req) =>
          req.id === requestId ? { ...req, status: "REJECTED" } : req
        )
      );
    } catch (error) {
      alert("Failed to reject request: " + error.response?.data?.message);
    }
  };

  if (loading) {
 
    return (
      <>
        <Navbar />
        <div className="container mt-4">
          <p>Loading...</p>
        </div>
      </>
    );
  }

  if (!isLoggedIn) {
    navigate('/')
    // return (
    //   <>
    //     <Navbar />
    //     <div className="container mt-4">
    //       <p className="error-message">User not logged in</p>
    //     </div>
    //   </>
    // );
  }

  return (
    <>
      <Navbar />
      <div className="container mt-4">
        <h2>Received Room Swap Requests</h2>
        {requests.length === 0 ? (
          <p>No swap requests received.</p>
        ) : (
          <table className="table table-striped">
            <thead>
              <tr>
                <th>#</th>
                <th>Applicant</th>
                <th>Message</th>
                <th>Status</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {requests.map((request, index) => (
                <tr key={request.id}>
                  <td>{index + 1}</td>
                  <td>{request.applicantName}</td>
                  <td>{request.applicationMessage}</td>
                  <td>{request.status}</td>
                  <td>
                    {request.status === "PENDING" && (
                      <>
                        <button
                          className="btn btn-success btn-sm me-2"
                          onClick={() => handleAccept(request.id)}
                        >
                          Accept
                        </button>
                        <button
                          className="btn btn-danger btn-sm"
                          onClick={() => handleReject(request.id)}
                        >
                          Reject
                        </button>
                      </>
                    )}
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        )}
      </div>
    </>
  );
};

export default ReceivedSwappingRequests;
