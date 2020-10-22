package by.yurovski.command.redirect;

import by.yurovski.command.Command;
import by.yurovski.enums.UserStatusEnum;
import by.yurovski.manager.PathManager;
import javax.servlet.http.HttpServletRequest;
public class AddNewImgPageRedirectCommand implements Command {
    private static Command instance = new AddNewImgPageRedirectCommand();
    private AddNewImgPageRedirectCommand (){}
    public static Command getInstance() {
        return instance;
    }

    @Override
    public String execute(HttpServletRequest request) {
        if (request.getSession().getAttribute("status")==null ||
                request.getSession().getAttribute("status")==UserStatusEnum.GUEST){
            return EmptyCommand.getInstance().execute(request);
        }
        return PathManager.getProperty("common.addNewImg");
    }

}
