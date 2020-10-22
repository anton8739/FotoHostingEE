package by.yurovski.manager;

import java.util.Locale;
import java.util.ResourceBundle;


public class PageContentManager {
    private static ResourceBundle resourceBundle;
    private static Locale locale = new Locale("ru", "RU");

    private PageContentManager() {}

    public static void setLocale (Locale loc) {
        locale = loc;
    }

    public static String getProperty (String key) {
        resourceBundle = ResourceBundle.getBundle("pageContent", locale);
        return resourceBundle.getString(key);
    }

}
