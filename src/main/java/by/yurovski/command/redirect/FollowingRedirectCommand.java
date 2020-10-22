package by.yurovski.command.redirect;

import by.yurovski.command.Command;
import by.yurovski.entity.User;
import by.yurovski.enums.UserStatusEnum;
import by.yurovski.exception.ServiceException;
import by.yurovski.manager.PathManager;
import by.yurovski.service.FollowerService;
import by.yurovski.service.FotoService;
import by.yurovski.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class FollowingRedirectCommand implements Command{
    private static final Logger LOGGER = Logger.getLogger(UserAccountRedirectCommand.class);
    private static Command instance = new FollowingRedirectCommand ();
    private FollowingRedirectCommand  (){}
    public static Command getInstance() {
        return instance;
    }

    @Override
    public String execute(HttpServletRequest request) {
        if (request.getSession().getAttribute("status")==null ||
                request.getSession().getAttribute("status")==UserStatusEnum.GUEST){
            return EmptyCommand.getInstance().execute(request);
        }
        int currentUserId;
        int loginedUserId=(int) request.getSession().getAttribute("id");
        if (request.getParameter("userId") == null){
            currentUserId=loginedUserId;
        }else{
            currentUserId=Integer.parseInt(request.getParameter("userId"));
        }
        try {
            User user=UserService.getInstance().getUserById(currentUserId);
            User loginedUser=UserService.getInstance().getUserById(loginedUserId);
            request.setAttribute("currentUser", user);
            request.setAttribute("numberOfLikes", UserService.getInstance().getNamberOfLikeOfAllUsersFoto(user));
            request.setAttribute("numberOfFotos", FotoService.getInstance().getNumberOfFotoOfCurrentUser(user));
            request.setAttribute("numberOfFollowers",FollowerService.getInstance().getNumberOfFollowers(user.getId()));
            request.setAttribute("numberOfFollowings",FollowerService.getInstance().getNumberOfFollowings(user.getId()));
            request.setAttribute("followingsMap", UserService.getInstance().getFolloweingsInformationMap(loginedUser,user));
        } catch (ServiceException e){
            LOGGER.info("Exception in LoginCommand", e);
        }
        return PathManager.getProperty("common.followings") ;
    }
}
