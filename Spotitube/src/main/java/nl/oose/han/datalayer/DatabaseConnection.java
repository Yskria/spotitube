package nl.oose.han.datalayer;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConnection {
    private Logger logger = Logger.getLogger(getClass().getName());
    private Properties properties;

    public DatabaseConnection() {
        properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("database.properties"));
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Can't access property file database.properties", e);
        }
    }

    public String connectionString()
    {
        return properties.getProperty("connectionString");
    }

}