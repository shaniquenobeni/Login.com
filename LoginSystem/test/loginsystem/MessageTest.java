/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loginsystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
/**
 *
 * @author ampfa
 */


public class MessageTest {

    private Message message;

    @BeforeEach
    public void setUp() {
        message = new Message(1, "+27831234567", "Hello World");
    }

    
    @Test
    public void testMessageIDIsGenerated() {
        assertNotNull(message.getMessageID(), "Message ID should be generated");
    }

    @Test
    public void testMessageIDIsTenCharsOrLess() {
        assertTrue(message.checkMessageID(),
                "Message ID should be <= 10 characters");
        assertTrue(message.getMessageID().length() <= 10);
    }

    
    @Test
    public void testValidRecipientCellWithPlus() {
        Message m = new Message(1, "+27831234567", "Hi");
        assertTrue(m.checkRecipientCell(), "Valid international cell should pass");
    }

    @Test
    public void testRecipientCellWithoutPlusFails() {
        Message m = new Message(1, "0831234567", "Hi");
        assertFalse(m.checkRecipientCell(), "Cell without '+' should fail");
    }

    @Test
    public void testNullRecipientFails() {
        Message m = new Message(1, null, "Hi");
        assertFalse(m.checkRecipientCell());
    }

   
    @Test
    public void testHashIsUppercase() {
        String hash = message.getMessageHash();
        assertEquals(hash, hash.toUpperCase(),
                "Hash should be fully uppercase");
    }

    @Test
    public void testHashContainsMessageNumber() {
        assertTrue(message.getMessageHash().contains(":1:"),
                "Hash should contain message number");
    }

    @Test
    public void testHashStartsWithIdPrefix() {
        String idPrefix = message.getMessageID().substring(0, 2);
        assertTrue(message.getMessageHash().startsWith(idPrefix),
                "Hash should start with first 2 chars of message ID");
    }

    @Test
    public void testHashUsesFirstAndLastWord() {
        Message m = new Message(3, "+27831234567", "Hello beautiful World");
        // Expected: <prefix>:3:HELLOWORLD
        assertTrue(m.getMessageHash().endsWith("HELLOWORLD"),
                "Hash should end with FIRST+LAST word uppercased");
    }

   
    @Test
    public void testSendMessageSetsSentStatus() {
        String result = message.sentMessage(1);
        assertEquals("Sent", message.getMessageStatus());
        assertTrue(result.toLowerCase().contains("sent"));
    }

    @Test
    public void testDisregardMessageSetsDisregardedStatus() {
        message.sentMessage(2);
        assertEquals("Disregarded", message.getMessageStatus());
    }

    @Test
    public void testStoreMessageSetsStoredStatus() {
        message.sentMessage(3);
        assertEquals("Stored", message.getMessageStatus());
    }

    @Test
    public void testInvalidChoiceReturnsInvalidMessage() {
        String result = message.sentMessage(99);
        assertEquals("Invalid choice.", result);
    }

    
    @Test
    public void testPrintMessageDetailsContainsAllFields() {
        String details = message.printMessageDetails();
        assertTrue(details.contains(message.getMessageID()));
        assertTrue(details.contains(message.getMessageHash()));
        assertTrue(details.contains("+27831234567"));
        assertTrue(details.contains("Hello World"));
    }
}