export default class Student {
    constructor(data) {
        this.id = data.id;
        this.firstName = data.firstName;
        this.lastName = data.lastName;
        this.email = data.email;
        this.roomNumber = data.roomNumber;
        this.hostelName = data.hostelName;
        this.floor = data.floor;
    }

    // Getter for full name
    get fullName() {
        return `${this.firstName} ${this.lastName}`;
    }
}
