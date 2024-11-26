export default class UserAuth {
    constructor(data) {
      this.id = data.id;
      this.token = data.token;
      this.email = data.email;
    }
  
    // Getter for user details
    get userDetails() {
      return { id: this.id, email: this.email };
    }
  }
  