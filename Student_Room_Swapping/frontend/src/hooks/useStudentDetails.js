import { useEffect, useState } from 'react';
import Student from '../models/Student';
import { fetchStudentDetails } from '../utils/httpStudent';
const useStudentDetails = (navigate) => {
    const [student, setStudent] = useState(null);
    const [error, setError] = useState(null);

    useEffect(() => {
        const loadStudentDetails = async () => {
            const studentId = localStorage.getItem("Id");
            const token = localStorage.getItem("token");

            if (!studentId || !token) {
                navigate('/'); // Redirect to login if not authenticated
                return;
            }

            try {
                const fetchedStudent = await fetchStudentDetails(studentId, token);
                setStudent(new Student(fetchedStudent));
            } catch (err) {
                setError(err.message);
            }
        };

        loadStudentDetails();
    }, [navigate]);

    return { student, error };
};

export default useStudentDetails;
