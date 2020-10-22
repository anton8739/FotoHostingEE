package by.yurovski.manager;

import java.util.ResourceBundle;

public class DBPropertyManager {

    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("DBconfig");

    private DBPropertyManager(){}

    public static String getProperty (String key){
        return resourceBundle.getString(key);
    }
}
