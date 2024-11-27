// src/model/SwapRequest.js
export default class SwapRequest {
    constructor(data) {
        this.applicantId = data.applicantId || ""; // Logged-in user ID
        this.recipientId = data.recipientId || ""; // Recipient ID
        this.applicationMessage = data.applicationMessage || ""; // Message to recipient
       
    }
}
