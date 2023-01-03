package com.example.natour21;

import com.example.natour21.Class.LoginPolicyMock;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Class test for email and password policy, according with AWS a password to be matched:
 * ^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,16}$
 *
 * - Length between: [8-16]
 * - One char: a-z;
 * - One char: A-Z;
 * - One number: 0-9;
 * - One special char: #?!@$ %^&*-;
 *
 * An email is a valid email in standard regex:
 * [a-zA-Z0-9\+\.\_\%\-\+]{1,256}\@[a-zA-Z0-9][a-zA-Z0-9\-]{0,64}(\.[a-zA-Z0-9][a-zA-Z0-9\-]{0,25})
 *
 * You can try it here:
 * https://regexr.com/
 *
 * Strategy used for testing:
 * Black Box - WECT
 */
public class PolicyTesting {

    LoginPolicyMock loginPolicyMock = new LoginPolicyMock();

    @Test
    public void emailValidPasswordValid() {
        String email = "support.natour21@libero.it";
        String password = "#Password123";

        assertTrue(loginPolicyMock.isValid(email,password));
    }

    @Test
    public void emailWithoutAtPasswordValid() {
        String email = "support.natour21";
        String password = "#Password123";

        assertFalse(loginPolicyMock.isValid(email,password));
    }

    @Test
    public void emailWithSpecialCharPasswordValid() {
        String email = "support.natour#21@libero.it";
        String password = "#Password123";

        assertFalse(loginPolicyMock.isValid(email,password));
    }

    @Test
    public void emailWithoutPrefixPasswordValid() {
        String email = "@libero.it";
        String password = "#Password123";

        assertFalse(loginPolicyMock.isValid(email,password));
    }

    @Test
    public void emailWithoutDomainPasswordValid() {
        String email = "support.natour21@";
        String password = "#Password123";

        assertFalse(loginPolicyMock.isValid(email,password));
    }

    @Test
    public void emailValidPasswordTooLong() {
        String email = "support.natour21@libero.it";
        String password = "#ThisPasswordIsToooooLong123";

        assertFalse(loginPolicyMock.isValid(email,password));
    }

    @Test
    public void emailValidPasswordTooShort() {
        String email = "support.natour21@libero.it";
        String password = "#Pa123";

        assertFalse(loginPolicyMock.isValid(email,password));
    }

    @Test
    public void emailValidPasswordWithoutAZ() {
        String email = "support.natour21@libero.it";
        String password = "#password123";

        assertFalse(loginPolicyMock.isValid(email,password));
    }

    @Test
    public void emailValidPasswordWithoutaz() {
        String email = "support.natour21@libero.it";
        String password = "#PASSWORD123";

        assertFalse(loginPolicyMock.isValid(email,password));
    }

    @Test
    public void emailValidPasswordWithoutNumber() {
        String email = "support.natour21@libero.it";
        String password = "#Password";

        assertFalse(loginPolicyMock.isValid(email,password));
    }

    @Test
    public void emailValidPasswordWithoutSpecial() {
        String email = "support.natour21@libero.it";
        String password = "Password123";

        assertFalse(loginPolicyMock.isValid(email,password));
    }

    @Test (expected = IllegalArgumentException.class)
    public void argumentValidNull() {
        loginPolicyMock.isValid("support.natour21@libero.it", null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void argumentNullValid() {
        loginPolicyMock.isValid(null, "#Password123");
    }

//    @Test (expected = IllegalArgumentException.class)
//    public void argumentNull() {
//        loginPolicyMock.isValid(null, null);
//    }

    @Test
    public void allInvalid() {
        String email = "libero.it";
        String password = "notagoodpassword";

        assertFalse(loginPolicyMock.isValid(email,password));
    }
}
