import React from 'react';
import Navbar from '../Components/Navbar';
import useStudentDetails from '../hooks/useStudentDetails';
import { useNavigate } from 'react-router-dom';
import '../App.css';

const AboutMe = () => {
    const navigate = useNavigate();
    const { student, error } = useStudentDetails(navigate);

    return (
        <>
            <Navbar />
            <div className="about-me">
                <h1>About Me</h1>
                {error && <div className="error-message">{error}</div>}
                {student ? (
                    <>
                        <p>
                            <strong>ID:</strong> {student.id}
                        </p>
                        <p>
                            <strong>Name:</strong> {student.fullName}
                        </p>
                        <p>
                            <strong>Email:</strong> {student.email}
                        </p>
                        <p>
                            <strong>Room Number:</strong> {student.roomNumber}
                        </p>
                        <p>
                            <strong>Hostel:</strong> {student.hostelName}, Floor {student.floor}
                        </p>
                    </>
                ) : (
                    <p>Student details are being loaded. Please wait...</p>
                )}
            </div>
        </>
    );
};

export default AboutMe;
