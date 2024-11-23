import React, { useEffect, useState } from "react";
import axios from "axios";
import Navbar from '../Components/Navbar'
import '../App.css'

const AboutMe = () => {
  const [studentDetails, setStudentDetails] = useState(null);
  const [error, setError] = useState("");

  useEffect(() => {
    const fetchStudentDetails = async () => {
      try {
        const studentId = localStorage.getItem("Id"); // Retrieve student ID from localStorage
        const token = localStorage.getItem("token"); // Retrieve JWT token from localStorage

        if (!studentId || !token) {
          setError("User not logged in");
          return;
        }

        const response = await axios.get(
          `http://localhost:8080/api/v1/student/auth/about-me?studentId=${studentId}`,
          {
            headers: {
              Authorization: `Bearer ${token}`, // Pass JWT for authorization
            },
          }
        );

        setStudentDetails(response.data);
      } catch (err) {
        setError(err.response?.data?.message || "Failed to fetch student details");
      }
    };

    fetchStudentDetails();
  }, []);

  if (error) {
    return <div className="error-message">{error}</div>;
  }

  if (!studentDetails) {
    return <div>Loading...</div>;
  }

  return (
    <>
    <Navbar/>
    <div className="about-me">
      <h1>About Me</h1>
      <p>
        <strong>ID:</strong> {studentDetails.id}
      </p>
      <p>
        <strong>Name:</strong> {studentDetails.firstName} {studentDetails.lastName}
      </p>
      <p>
        <strong>Email:</strong> {studentDetails.email}
      </p>
      <p>
        <strong>Room Number:</strong> {studentDetails.roomNumber}
      </p>
      <p>
        <strong>Hostel:</strong> {studentDetails.hostelName}, Floor {studentDetails.floor}
      </p>
    </div>
    </>
  );
};

export default AboutMe;
