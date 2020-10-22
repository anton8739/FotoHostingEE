package by.yurovski.command.comment;

import by.yurovski.command.Command;
import by.yurovski.command.redirect.EmptyCommand;
import by.yurovski.enums.UserStatusEnum;

import javax.servlet.http.HttpServletRequest;

public class SortCommentCommand implements Command{
    private static Command instance = new SortCommentCommand();
    private SortCommentCommand (){}
    public static Command getInstance() {
        return instance;
    }

    @Override
    public String execute(HttpServletRequest request) {
        if (request.getSession().getAttribute("status")==null ||
                request.getSession().getAttribute("status")==UserStatusEnum.GUEST){
            return EmptyCommand.getInstance().execute(request);
        }
        return null;
    }

}
