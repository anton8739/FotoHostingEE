package by.yurovski.command.redirect;

import by.yurovski.command.Command;
import by.yurovski.manager.PathManager;

import javax.servlet.http.HttpServletRequest;

public class RegistrationPageRedirectCommand implements Command {
    private static Command instance = new RegistrationPageRedirectCommand();
    private RegistrationPageRedirectCommand  (){}
    public static Command getInstance() {
        return instance;
    }

    @Override
    public String execute(HttpServletRequest request) {

        if (request.getSession().getAttribute("user")!=null){
            return UserAccountRedirectCommand.getInstance().execute(request);
        } else {
            return PathManager.getProperty("common.registrationPage");
        }

    }
}
