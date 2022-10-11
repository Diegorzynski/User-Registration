package org.registration;

import org.junit.Test;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

public class ValidationsTest {

    @Test
    public void emailWhenValidandInvalid(){
        String validEmail = "testmail@mail.com";
        String invalidEmail = "test.test.com";

        assertTrue(EmailValidation.isValid(validEmail));
        assertFalse(EmailValidation.isValid(invalidEmail));
    }

    @Test
    public void peselWhenValidAndInvalid() throws UnsupportedEncodingException {
        String validPesel = "55092056374";
        String invalidPesel = "55092056370";

        assertTrue(PeselValidation.isValid(validPesel));
        assertFalse(PeselValidation.isValid(invalidPesel));
    }





}