export default class SwapRequestList {
    constructor(data) {
      this.id = data.id;
      this.applicantName = data.applicantName;
      this.applicationMessage = data.applicationMessage;
      this.status = data.status;
    }
  
    // Method to check if a request is pending
    isPending() {
      return this.status === "PENDING";
    }
  }
  