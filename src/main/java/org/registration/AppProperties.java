package org.registration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.Properties;

public class AppProperties {

    private static Logger log = LogManager.getLogger(AppProperties.class);

    public static Properties properties = new Properties();



    public static void loadProperties(){

        try{
                File file = new File("src/main/resources/app.config");

                InputStream is = new FileInputStream(file);

                properties.load(is);

                log.trace("Properties loaded.");


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Properties getProperties() {
        return properties;
    }
}
