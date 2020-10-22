package by.yurovski.command.foto;


import by.yurovski.command.Command;
import by.yurovski.command.redirect.EmptyCommand;
import by.yurovski.command.user.DeleteAccountCommand;
import by.yurovski.entity.Foto;
import by.yurovski.entity.User;
import by.yurovski.enums.UserStatusEnum;
import by.yurovski.exception.ServiceException;
import by.yurovski.manager.PathManager;
import by.yurovski.service.FollowerService;
import by.yurovski.service.FotoService;
import by.yurovski.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class DeleteImgCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(DeleteAccountCommand.class);
    private static Command instance = new DeleteImgCommand();
    private DeleteImgCommand (){}
    public static Command getInstance() {
        return instance;
    }

    @Override
    public String execute(HttpServletRequest request) {
        if (request.getSession().getAttribute("status")==null){
            return EmptyCommand.getInstance().execute(request);
        }
        int currentUserId=(int) request.getSession().getAttribute("id");
        String fotoName=request.getParameter("foto");
        try {
            Foto foto = FotoService.getInstance().getFotoByName(fotoName);
            FotoService.getInstance().delete(foto);
            User user=UserService.getInstance().getUserById(currentUserId);
            request.setAttribute("currentUser", user);
            request.setAttribute("numberOfLikes", UserService.getInstance().getNamberOfLikeOfAllUsersFoto(user));
            request.setAttribute("numberOfFotos", FotoService.getInstance().getNumberOfFotoOfCurrentUser(user));
            request.setAttribute("numberOfFollowers",FollowerService.getInstance().getNumberOfFollowers(user.getId()));
            request.setAttribute("numberOfFollowings",FollowerService.getInstance().getNumberOfFollowings(user.getId()));
            request.setAttribute("fotoMap",FotoService.getInstance().getFotoInformationMap(user.getId(),user.getId()));
        }
        catch (ServiceException e){
            LOGGER.info("Exception in DeleteImgCommand", e);

        }


        return PathManager.getProperty("common.userAccountPage");
    }
}
