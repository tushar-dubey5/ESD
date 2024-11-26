import axios from 'axios';

// Function to log in a user
export const loginUser = async (email, password) => {
  try {
    const response = await axios.post('http://localhost:8080/api/v1/student/auth/login', {
      email,
      password,
    });
    return response.data; // Return the token
  } catch (error) {
    throw new Error('Invalid email or password.');
  }
};

// Utility to decode JWT
export const decodeToken = (token, jwtDecode) => {
  const decoded = jwtDecode(token);
  return { id: decoded.id }; // Assuming the token contains the user ID
};
