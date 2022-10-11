package org.registration;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SaveUserToDatabase {

    private static Logger log = LogManager.getLogger(SaveUserToDatabase.class);

    private static final String QUOTE = "'";

    private static final String COMMA = ";";
    private static final String QUOTECOMMA = "',";

    private static Connection connection = JdbcConnection.getConnection();

    public static void addUser(User user) {
        try (Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS USERS (FIRST_NAME VARCHAR(100), LAST_NAME VARCHAR(100)," +
                    "PESEL VARCHAR(11), EMAIL VARCHAR(100), CITY VARCHAR(100), ZIPCODE VARCHAR(20))");

           statement.execute("INSERT INTO users VALUES (" +
                                  QUOTE + user.getName() + QUOTECOMMA +
                                  QUOTE + user.getLastName() + QUOTECOMMA +
                                  QUOTE + user.getPesel() + QUOTECOMMA +
                                  QUOTE + user.getEmail() + QUOTECOMMA +
                                  QUOTE + user.getCity() + QUOTECOMMA +
                                  QUOTE + user.getZipCode() + QUOTE + ")");

            log.trace("User {} added successfully", user.getName());
            EmailSender.sendEmail(user);

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
