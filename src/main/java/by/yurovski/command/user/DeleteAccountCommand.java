package by.yurovski.command.user;

import by.yurovski.command.Command;
import by.yurovski.command.redirect.EmptyCommand;
import by.yurovski.entity.User;
import by.yurovski.enums.UserStatusEnum;
import by.yurovski.exception.ServiceException;
import by.yurovski.manager.PageContentManager;
import by.yurovski.manager.PathManager;
import by.yurovski.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class DeleteAccountCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(DeleteAccountCommand.class);
    private static Command instance = new DeleteAccountCommand();
    private DeleteAccountCommand(){}
    public static Command getInstance() {
        return instance;
    }
    @Override
    public String execute(HttpServletRequest request) {
        if (request.getSession().getAttribute("status")==null){
            return EmptyCommand.getInstance().execute(request);
        }
        int userId = (int) request.getSession().getAttribute("id");
        request.getSession().invalidate();
        try {
            User user = UserService.getInstance().getUserById(userId);
            UserService.getInstance().delete(user);
            UserService.getInstance().delete(user);
            return PathManager.getProperty("common.main");
        }
        catch (ServiceException e){
                LOGGER.info("Exception in DeleteAccountCommand", e);

            }
        return PathManager.getProperty("common.main");
    }
}
