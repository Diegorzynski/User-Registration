package org.registration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JdbcConnection {

    private static Logger log = LogManager.getLogger("JdbcConnection");
    private static String dataBase;
    private static final String username;
    private static final String password;

    private static String url;


    static {
        AppProperties.loadProperties();
        username = AppProperties.getProperties().getProperty("db.username");
        password = AppProperties.getProperties().getProperty("db.password");
        dataBase = AppProperties.getProperties().getProperty("database");
        url = "jdbc:mysql://localhost:3306/" + dataBase;
    }

    public static Connection getConnection(){
        Connection connection;
        try{
            connection = DriverManager.getConnection(url, username, password);
            log.trace("Connected to database {}", dataBase);
            return connection;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
