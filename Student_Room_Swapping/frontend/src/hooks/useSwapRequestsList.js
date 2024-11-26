import { useEffect, useState, useCallback } from "react";
import { fetchSwapRequests } from "../utils/httpSwapRequestList";
import SwapRequestList from "../models/SwapRequestList";

function useSwapRequestsList() {
  const [requests, setRequests] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  // Define loadRequests as a reusable function using useCallback
  const loadRequests = useCallback(async () => {
    const token = localStorage.getItem("token");
    const recipientId = localStorage.getItem("Id");
     
    if (!token || !recipientId) {
      setLoading(false);
      setIsLoggedIn(false)
      return;
    }
    setIsLoggedIn(true)
    try {
      setLoading(true); // Ensure loading state is reset when reloading
      const data = await fetchSwapRequests(recipientId, token);
      const mappedRequests = data.map((item) => new SwapRequestList(item));
      console.log("Mapped Requests:", mappedRequests);
      setRequests(mappedRequests);
    } catch (err) {
      setError(err.message);
    } finally {
      setLoading(false);
    }
  }, []); // useCallback ensures that the function reference is stable

  // Automatically fetch requests on component mount
  useEffect(() => {
    loadRequests();
  }, [loadRequests]);

  return { requests, loading, error, reload: loadRequests,isLoggedIn };
}

export default useSwapRequestsList;
