import axios from "axios";

// Fetch received swap requests
export const fetchSwapRequests = async (recipientId, token) => {
  try {
    const response = await axios.get(
      `http://localhost:8080/api/v1/swap/recipient/${recipientId}`,
      { headers: { Authorization: `Bearer ${token}` } }
    );
    console.log("From utils: Reponse", response)
    console.log("From Utils: data: ",response.data)
    return response.data;
  } catch (error) {
    throw new Error("Failed to fetch swap requests: " + error.message);
  }
};

// Accept a swap request
export const acceptSwapRequest = async (requestId, recipientMessage, token) => {
  try {
    await axios.post(
      `http://localhost:8080/api/v1/swap/accept/${requestId}`,
      recipientMessage,
      { headers: { Authorization: `Bearer ${token}` } }
    );
  } catch (error) {
    throw new Error("Failed to accept request: " + error.message);
  }
};

// Reject a swap request
export const rejectSwapRequest = async (requestId, recipientMessage, token) => {
  try {
    await axios.post(
      `http://localhost:8080/api/v1/swap/reject/${requestId}?recipientMessage=${encodeURIComponent(
        recipientMessage
      )}`,
      null,
      { headers: { Authorization: `Bearer ${token}` } }
    );
  } catch (error) {
    throw new Error("Failed to reject request: " + error.message);
  }
};
