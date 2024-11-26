import { useState } from 'react';
import { loginUser, decodeToken } from '../utils/authUtils';
import {jwtDecode} from 'jwt-decode'; // jwtDecode should be passed if not available globally
import { useNavigate } from 'react-router-dom';

const useAuth = () => {
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleLogin = async (email, password) => {
    try {
      const token = await loginUser(email, password); // Fetch token
      localStorage.setItem('token', token); // Store token
      const decodedToken = decodeToken(token, jwtDecode); // Decode token to get user ID
      localStorage.setItem('Id', decodedToken.id); // Store user ID
      navigate('/landing'); // Redirect on success
    } catch (err) {
      setError(err.message);
    }
  };

  return { error, handleLogin };
};

export default useAuth;
