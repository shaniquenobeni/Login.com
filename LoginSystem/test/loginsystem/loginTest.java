/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package loginsystem;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author ampfa
 */
public class loginTest {
    
  
    @Test
    public void testUsernameCorrectlyFormatted() {
        Login login = new Login("Kyle", "User", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(login.checkUserName("kyl_1"));
    }

    @Test
    public void testUsernameIncorrectlyFormatted() {
        Login login = new Login();
        login.setUsername("kyle!!!!!!");
        String expected = "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        assertEquals(expected, login.registerUser());
    }

    @Test
    public void testPasswordMeetsComplexity() {
        Login login = new Login();
        login.setUsername("kyl_1");
        login.setPassword("Ch&&sec@ke99!");
        login.setCellPhoneNumber("+27838968976");
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"));
    }

    @Test
    public void testPasswordDoesNotMeetComplexity() {
        Login login = new Login();
        login.setUsername("kyl_1");
        login.setPassword("password");
        String expected = "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        assertEquals(expected, login.registerUser());
    }

    @Test
    public void testCellPhoneCorrectlyFormatted() {
        Login login = new Login();
        assertTrue(login.checkCellPhoneNumber("+27838968976"));
    }

    @Test
    public void testCellPhoneIncorrectlyFormatted() {
        Login login = new Login();
        login.setUsername("kyl_1");
        login.setPassword("Ch&&sec@ke99!");
        login.setCellPhoneNumber("08966553");
        String expected = "Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.";
        assertEquals(expected, login.registerUser());
    }


    @Test
    public void testLoginSuccessful() {
        Login login = new Login("Kyle", "User", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(login.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    public void testLoginFailed() {
        Login login = new Login("Kyle", "User", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertFalse(login.loginUser("kyl_1", "wrongpassword"));
    }

    @Test
    public void testCheckUserNameTrue() {
        Login login = new Login();
        assertTrue(login.checkUserName("kyl_1"));
    }

    @Test
    public void testCheckUserNameFalse() {
        Login login = new Login();
        assertFalse(login.checkUserName("kyle!!!!!!"));
    }

    @Test
    public void testCheckPasswordComplexityTrue() {
        Login login = new Login();
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"));
    }

    @Test
    public void testCheckPasswordComplexityFalse() {
        Login login = new Login();
        assertFalse(login.checkPasswordComplexity("password"));
    }

    @Test
    public void testCheckCellPhoneNumberTrue() {
        Login login = new Login();
        assertTrue(login.checkCellPhoneNumber("+27838968976"));
    }

    @Test
    public void testCheckCellPhoneNumberFalse() {
        Login login = new Login();
        assertFalse(login.checkCellPhoneNumber("08966553"));
    }

    private void assertTrue(boolean checkUserName) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void assertEquals(String expected, String registerUser) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void assertFalse(boolean loginUser) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
