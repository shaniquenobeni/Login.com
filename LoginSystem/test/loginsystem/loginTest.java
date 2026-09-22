package loginsystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class loginTest {

    private Login login;

    @BeforeEach
    public void setUp() {
        login = new Login();
    }

    
    @Test
    public void testValidUsername() {
        assertTrue(login.checkUserName("ab_cd"),
                "Valid username must contain _ and be <= 5 chars");
    }

    @Test
    public void testUsernameMissingUnderscore() {
        assertFalse(login.checkUserName("abcde"));
    }

    @Test
    public void testUsernameTooLong() {
        assertFalse(login.checkUserName("abc_de"));
    }

    @Test
    public void testUsernameEmpty() {
        assertFalse(login.checkUserName(""));
    }

    
    @Test
    public void testValidPassword() {
        assertTrue(login.checkPasswordComplexity("Password1!"),
                "Password needs 8+ chars, capital, digit, special");
    }

    @Test
    public void testPasswordTooShort() {
        assertFalse(login.checkPasswordComplexity("Pa1!"));
    }

    @Test
    public void testPasswordMissingCapital() {
        assertFalse(login.checkPasswordComplexity("password1!"));
    }

    @Test
    public void testPasswordMissingDigit() {
        assertFalse(login.checkPasswordComplexity("Password!"));
    }

    @Test
    public void testPasswordMissingSpecialChar() {
        assertFalse(login.checkPasswordComplexity("Password1"));
    }

    
    @Test
    public void testValidCellNumber() {
        assertTrue(login.checkCellPhoneNumber("+27831234567"));
    }

    @Test
    public void testCellWithoutInternationalCode() {
        assertFalse(login.checkCellPhoneNumber("0831234567"));
    }

    @Test
    public void testCellEmpty() {
        assertFalse(login.checkCellPhoneNumber(""));
    }

    
    @Test
    public void testSuccessfulLogin() {
        login.setUsername("ab_cd");
        login.setPassword("Password1!");
        login.setFirstName("John");
        login.setLastName("Doe");

        assertTrue(login.loginUser("ab_cd", "Password1!"));
    }

    @Test
    public void testLoginWrongPassword() {
        login.setUsername("ab_cd");
        login.setPassword("Password1!");

        assertFalse(login.loginUser("ab_cd", "WrongPass1!"));
    }

    @Test
    public void testLoginWrongUsername() {
        login.setUsername("ab_cd");
        login.setPassword("Password1!");

        assertFalse(login.loginUser("xx_yy", "Password1!"));
    }

    @Test
    public void testReturnLoginStatusSuccessContainsWelcome() {
        login.setFirstName("John");
        login.setLastName("Doe");
        String status = login.returnLoginStatus(true);
        assertTrue(status.contains("Welcome") || status.contains("John"),
                "Success status should welcome the user");
    }

    @Test
    public void testReturnLoginStatusFailureMentionsFailure() {
        String status = login.returnLoginStatus(false);
        assertTrue(status.toLowerCase().contains("fail")
                        || status.toLowerCase().contains("incorrect"),
                "Failure status should indicate failed login");
    }
}