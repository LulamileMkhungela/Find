package co.za.lulamile.find.chats;

import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

public class MessageDetails {

    @ServerTimestamp
    private Date sentAt;
    private String sender;
    private String message;
    private String imageUrl;

    public MessageDetails() {
        //
        //Do not delete
    }

    public MessageDetails(String sender, String message, String imageUrl) {
        this.sentAt = null;
        this.sender = sender;
        this.message = message;
        this.imageUrl = imageUrl;
    }

    public Date getSentAt() {
        return sentAt;
    }

    public String getSender() {
        return sender;
    }

    public String getMessage() {
        return message;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}

