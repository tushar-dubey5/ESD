import React, { useState, useEffect } from "react";
import axios from "axios";

const RequestSwapping = () => {
  const [formData, setFormData] = useState({
    applicantId: "", // Auto-filled with logged-in user's ID
    recipientId: "",
    applicationMessage: "",
  });

  useEffect(() => {
    const userId = localStorage.getItem("Id"); // Fetch logged-in user ID from localStorage
    if (userId) {
      setFormData((prev) => ({ ...prev, applicantId: userId }));
    } else {
      alert("User ID not found. Please log in again."); // Optional error handling
    }
  }, []);

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const token = localStorage.getItem("token"); // Fetch token from localStorage
      console.log("Hello: ", formData);
      const response = await axios.post(
        "http://localhost:8080/api/v1/swap/request",
        formData,
        { headers: { Authorization: `Bearer ${token}` } }
      );
      alert("Swap request created successfully!");
    } catch (error) {
      alert("Failed to create swap request: " + error.response.data.message);
    }
  };

  return (
    <div>
      <h2>Create Room Swapping Request</h2>
      <form onSubmit={handleSubmit}>
        <div className="mb-3">
          <label htmlFor="applicantId" className="form-label">Applicant</label>
          <input
            type="text"
            className="form-control"
            id="applicantId"
            name="applicantId"
            value={formData.applicantId}
            readOnly
          />
        </div>
        <div className="mb-3">
          <label htmlFor="recipientId" className="form-label">Recipient</label>
          <input
            type="text"
            className="form-control"
            id="recipientId"
            name="recipientId"
            value={formData.recipientId}
            onChange={handleChange}
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="applicationMessage" className="form-label">Message</label>
          <textarea
            className="form-control"
            id="applicationMessage"
            name="applicationMessage"
            value={formData.applicationMessage}
            onChange={handleChange}
            required
          />
        </div>
        <button type="submit" className="btn btn-primary">Submit</button>
      </form>
    </div>
  );
};

export default RequestSwapping;