package loginsystem;

import java.util.Random;

public class Message {
    private String messageID;
    private int messageNumber;
    private String recipientCell;
    private String messageContent;
    private String messageHash;
    private String messageStatus;

  
    public Message(int messageNumber, String recipientCell, String messageContent) {
        this.messageNumber = messageNumber;
        this.recipientCell = recipientCell;
        this.messageContent = messageContent;
        this.messageID = generateMessageID();
        this.messageHash = createMessageHash();
    }

    
    public boolean checkMessageID() {
        return messageID != null && messageID.length() <= 10;
    }

    
    public boolean checkRecipientCell() {
        if (recipientCell == null) return false;
        return recipientCell.startsWith("+") && recipientCell.length() <= 10;
    }

    
    public String createMessageHash() {
        if (messageID == null || messageID.length() < 2 || messageContent == null || messageContent.trim().isEmpty()) {
            return "";
        }
        String idPrefix = messageID.substring(0, 2);
        
        
        String cleaned = messageContent.replaceAll("[^a-zA-Z ]", "").trim();
        String[] words = cleaned.split("\\s+");
        
        String firstWord = words.length > 0 ? words[0] : "";
        String lastWord = words.length > 0 ? words[words.length - 1] : "";
        
        return (idPrefix + ":" + messageNumber + ":" + firstWord + lastWord).toUpperCase();
    }

    
    private String generateMessageID() {
        Random rand = new Random();
        long id = 1000000000L + rand.nextLong(900000000L);
        return String.valueOf(id);
    }

    
    public String sentMessage(int choice) {
        switch (choice) {
            case 1:
                this.messageStatus = "Sent";
                return "Message successfully sent.";
            case 2:
                this.messageStatus = "Disregarded";
                return "Press 0 to delete the message.";
            case 3:
                this.messageStatus = "Stored";
                return "Message successfully stored.";
            default:
                return "Invalid choice.";
        }
    }

   
    public String printMessageDetails() {
        return "Message ID: " + messageID + "\n" +
               "Message Hash: " + messageHash + "\n" +
               "Recipient: " + recipientCell + "\n" +
               "Message: " + messageContent;
    }

    
    public String getMessageID() { return messageID; }
    public String getMessageHash() { return messageHash; }
    public String getRecipientCell() { return recipientCell; }
    public String getMessageContent() { return messageContent; }
    public String getMessageStatus() { return messageStatus; }
}