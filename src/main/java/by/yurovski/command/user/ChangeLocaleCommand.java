package by.yurovski.command.user;

import by.yurovski.command.Command;
import by.yurovski.command.redirect.UserAccountRedirectCommand;
import by.yurovski.manager.PageContentManager;
import by.yurovski.manager.PathManager;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

public class ChangeLocaleCommand implements Command {
    private static Command instance = new ChangeLocaleCommand();
    private ChangeLocaleCommand (){}
    public static Command getInstance() {
        return instance;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String localeStr = null;
        String loc = request.getParameter("language");
        Locale locale;

        switch (loc.toLowerCase()) {
            case "eng":
                locale = new Locale("en", "US");
                localeStr = "en_US";
                break;
            case "ru":
                locale = new Locale("ru", "RU");
                localeStr = "ru_RU";
                break;
            default:
                locale = new Locale("ru", "RU");
        }
        PageContentManager.setLocale(locale);
        request.getSession().setAttribute("locale", localeStr);
        if(request.getSession().getAttribute("user") !=null){
            return UserAccountRedirectCommand.getInstance().execute(request);
        } else {
            return PathManager.getProperty("common.main");
        }

    }
}
