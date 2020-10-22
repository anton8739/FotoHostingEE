package by.yurovski.command.user;


import by.yurovski.command.Command;
import by.yurovski.command.redirect.EmptyCommand;
import by.yurovski.enums.UserStatusEnum;
import by.yurovski.manager.PathManager;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command {
    private static Command instance = new LogoutCommand();
    private LogoutCommand(){}
    public static Command getInstance() {
        return instance;
    }
    @Override
    public String execute(HttpServletRequest request) {
        if (request.getSession().getAttribute("status")==null ||
                request.getSession().getAttribute("status")==UserStatusEnum.GUEST){
            return EmptyCommand.getInstance().execute(request);
        }
        request.getSession().invalidate();
        return PathManager.getProperty("common.main");
    }
}
