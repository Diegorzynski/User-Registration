package org.registration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EmailValidation {

    private static Logger log = LogManager.getLogger(EmailValidation.class);

    private static final String regexPattern = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

    public static boolean isValid(String email){
        boolean isValid = email.matches(regexPattern);
        log.trace("{} validation result: {}",email, isValid);
        return isValid;
    }
}
