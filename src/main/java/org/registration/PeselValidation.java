package org.registration;

import java.io.UnsupportedEncodingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PeselValidation {

    private static final Logger log = LogManager.getLogger(PeselValidation.class);

    private static boolean valid = false;


    public static boolean isValid(String pesel) throws UnsupportedEncodingException {
        if(pesel.length() != 11){
            valid = false;
            log.warn("Pesel {} is not valid", pesel);
            return false;
        }
        else {
            byte[] peselBytes = pesel.getBytes("UTF-16");
            for (int i = 0; i < 11; i++){
                peselBytes[i] = Byte.parseByte(pesel.substring(i, i+1));
            }
            if (checkSum(peselBytes) && checkMonth(peselBytes) && checkDay(peselBytes)) {
                valid = true;
                log.trace("Pesel {} is valid", pesel);
                return true;
            }
            else {
                valid = false;
                log.warn("Pesel {} is not valid", pesel);
                return false;
            }
        }
    }


    private static boolean checkSum(byte[] pesel) {
        int sum = 1 * pesel[0] +
                3 * pesel[1] +
                7 * pesel[2] +
                9 * pesel[3] +
                1 * pesel[4] +
                3 * pesel[5] +
                7 * pesel[6] +
                9 * pesel[7] +
                1 * pesel[8] +
                3 * pesel[9];
        sum %= 10;
        sum = 10 - sum;
        sum %= 10;

        if (sum == pesel[10]) {
            return true;
        }
        else {
            return false;
        }
    }

    private static boolean checkMonth(byte[] pesel) {
        int month = getBirthMonth(pesel);
        int day = getBirthDay(pesel);
        if (month > 0 && month < 13) {
            return true;
        }
        else {
            return false;
        }
    }

    private static int getBirthDay(byte[] pesel) {
        int day;
        day = 10 * pesel[4];
        day += pesel[5];
        return day;
    }

    private static int getBirthMonth(byte[] pesel) {
        int month;
        month = 10 * pesel[2];
        month += pesel[3];
        if (month > 80 && month < 93) {
            month -= 80;
        }
        else if (month > 20 && month < 33) {
            month -= 20;
        }
        else if (month > 40 && month < 53) {
            month -= 40;
        }
        else if (month > 60 && month < 73) {
            month -= 60;
        }
        return month;
    }

    private static boolean checkDay(byte[] pesel) {
        int year = getBirthYear(pesel);
        int month = getBirthMonth(pesel);
        int day = getBirthDay(pesel);
        if ((day >0 && day < 32) &&
                (month == 1 || month == 3 || month == 5 ||
                        month == 7 || month == 8 || month == 10 ||
                        month == 12)) {
            return true;
        }
        else if ((day >0 && day < 31) &&
                (month == 4 || month == 6 || month == 9 ||
                        month == 11)) {
            return true;
        }
        else if ((day >0 && day < 30 && leapYear(year)) ||
                (day >0 && day < 29 && !leapYear(year))) {
            return true;
        }
        else {
            return false;
        }
    }

    private static boolean leapYear(int year) {
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)
            return true;
        else
            return false;
    }

    private static int getBirthYear(byte[] pesel) {
        int year;
        int month;
        year = 10 * pesel[0];
        year += pesel[1];
        month = 10 * pesel[2];
        month += pesel[3];
        if (month > 80 && month < 93) {
            year += 1800;
        }
        else if (month > 0 && month < 13) {
            year += 1900;
        }
        else if (month > 20 && month < 33) {
            year += 2000;
        }
        else if (month > 40 && month < 53) {
            year += 2100;
        }
        else if (month > 60 && month < 73) {
            year += 2200;
        }
        return year;
    }

}
