package by.yurovski.command.redirect;

import by.yurovski.command.Command;

import javax.servlet.http.HttpServletRequest;

public class ErrorPageRedirectCommand implements Command{
    private static Command instance = new ErrorPageRedirectCommand();
    private ErrorPageRedirectCommand (){}
    public static Command getInstance() {
        return instance;
    }

    @Override
    public String execute(HttpServletRequest request) {
        return null;
    }
}
