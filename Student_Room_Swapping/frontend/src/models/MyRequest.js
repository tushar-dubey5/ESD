export default class MyRequest {
    constructor(data) {
        this.id = data.id;
        this.recipientName = data.recipientName;
        this.applicationMessage = data.applicationMessage;
        this.status = data.status;
        this.recipientMessage = data.recipientMessage;
    }
}
