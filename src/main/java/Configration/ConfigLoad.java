package Configration;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoad {

    public static DBConfigrations getDatabaseConfig() {
        try (InputStream input = new FileInputStream("resources/config/db-config.properties")) {
            DBConfigrations db = new DBConfigrations();

            Properties prop = new Properties();

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            db.setClassname(prop.getProperty("db.classname"));
            db.setUrl(prop.getProperty("db.url"));
            db.setUsername(prop.getProperty("db.user"));
            db.setPassword(prop.getProperty("db.password"));
            return db;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static DriverConfigration getDriverConfig() {
        DriverConfigration driverConfigration = new DriverConfigration();
        try (InputStream input = new FileInputStream("resources/config/driver-config.properties")) {

            Properties prop = new Properties();

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            driverConfigration.setDrivertype(DriverConfigration.DriverTypes.valueOf(prop.getProperty("driver.type")));
        } catch (IOException ex) {
            ex.printStackTrace();
            driverConfigration.setDrivertype(DriverConfigration.DriverTypes.CHROME);
        }
        return driverConfigration;
    }

}
