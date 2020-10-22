package by.yurovski.command.redirect;

import by.yurovski.command.Command;
import by.yurovski.manager.PathManager;

import javax.servlet.http.HttpServletRequest;

public class LoginPageRedirectCommand implements Command{
    private static Command instance = new LoginPageRedirectCommand();
    private LoginPageRedirectCommand  (){}
    public static Command getInstance() {
        return instance;
    }

    @Override
    public String execute(HttpServletRequest request) {

        if (request.getSession().getAttribute("user")!=null){
            return UserAccountRedirectCommand.getInstance().execute(request);
        } else {
            return PathManager.getProperty("common.loginPage");
        }
    }
}
