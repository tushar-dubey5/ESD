import axios from 'axios';

export const fetchStudentDetails = async (studentId, token) => {
    try {
        const response = await axios.get(
            `http://localhost:8080/api/v1/student/auth/about-me?studentId=${studentId}`,
            {
                headers: {
                    Authorization: `Bearer ${token}`,
                },
            }
        );
        return response.data;
    } catch (error) {
        throw new Error(error.response?.data?.message || "Failed to fetch student details");
    }
};
