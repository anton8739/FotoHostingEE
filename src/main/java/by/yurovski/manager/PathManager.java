package by.yurovski.manager;

import java.util.ResourceBundle;

public class PathManager {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("path");
    private PathManager() {}
    public static String getProperty (String key) {
        return resourceBundle.getString(key);
    }
}
