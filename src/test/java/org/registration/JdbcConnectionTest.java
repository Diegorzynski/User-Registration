package org.registration;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.sql.*;

import static org.junit.Assert.*;

public class JdbcConnectionTest {

    @Test
    public void connectToDb(){
        Connection connection = JdbcConnection.getConnection();

        assertNotNull(connection);
        assertTrue(connection instanceof Connection);
    }

    @Test
    public void insertNewUser() throws UnsupportedEncodingException {
        User user1 = new User("John","Smith","Lodz","90-423");
        user1.setEmail("john@email.com");
        user1.setPesel("55092056374");

        SaveUserToDatabase.addUser(user1);

    }

    @Test
    public void sendEmail() throws UnsupportedEncodingException {
        User user1 = new User("Maria","Costa","Lodz","90-423");
        user1.setEmail("maria@email.com");
        user1.setPesel("68091094573");

        SaveUserToDatabase.addUser(user1);

    }






}