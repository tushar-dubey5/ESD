import React from "react";
import Navbar from "../Components/Navbar";
import { acceptSwapRequest, rejectSwapRequest } from "../utils/httpSwapRequestList";
import useSwapRequestsList from "../hooks/useSwapRequestsList";
import { useNavigate } from "react-router-dom";


const ReceivedSwappingRequests = () => {
  const navigate = useNavigate()
  const { requests, loading, error, reload, isLoggedIn } = useSwapRequestsList();
  if(!isLoggedIn){
    navigate('/')
  }
  const token = localStorage.getItem("token");
   console.log("Components request: ",requests)
   console.log("Loading ",loading)
  const handleAccept = async (requestId) => {
    const recipientMessage = prompt("Enter a message for the applicant:");
    if (!recipientMessage) return;

    try {
      await acceptSwapRequest(requestId, recipientMessage, token);
      alert("Request accepted!");
      reload();
    } catch (error) {
      alert(error.message);
    }
  };

  const handleReject = async (requestId) => {
    const recipientMessage = prompt("Enter a message for the applicant:");
    if (!recipientMessage) return;

    try {
      await rejectSwapRequest(requestId, recipientMessage, token);
      alert("Request rejected!");
    } catch (error) {
      alert(error.message);
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
                    {request.isPending() && (
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
