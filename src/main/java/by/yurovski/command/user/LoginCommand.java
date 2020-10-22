package by.yurovski.command.user;


import by.yurovski.command.Command;
import by.yurovski.command.redirect.UserAccountRedirectCommand;
import by.yurovski.entity.User;
import by.yurovski.exception.ServiceException;
import by.yurovski.manager.PageContentManager;
import by.yurovski.manager.PathManager;
import by.yurovski.service.FollowerService;
import by.yurovski.service.FotoService;
import by.yurovski.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(LoginCommand.class);
    private static Command instance = new LoginCommand();
    private LoginCommand(){}
    public static Command getInstance() {
        return instance;
    }
        @Override
        public String execute(HttpServletRequest request) {
            if (request.getSession().getAttribute("user")!=null){
                return UserAccountRedirectCommand.getInstance().execute(request);
            }
            String page;
            String login=request.getParameter("name");
            String password=request.getParameter("password");
            try {
                User user=UserService.getInstance().checkLoginPassword(login, password);
                if(user != null){
                    if (user.isEnabled()){
                        request.getSession().setAttribute("status", user.getStatus());
                        request.getSession().setAttribute("user", user.getLogin());
                        request.getSession().setAttribute("id", user.getId());
                        request.getSession().setAttribute("loginedUser", user);
                        request.setAttribute("currentUser", user);
                        request.setAttribute("numberOfLikes", UserService.getInstance().getNamberOfLikeOfAllUsersFoto(user));
                        request.setAttribute("numberOfFotos", FotoService.getInstance().getNumberOfFotoOfCurrentUser(user));
                        request.setAttribute("numberOfFollowers",FollowerService.getInstance().getNumberOfFollowers(user.getId()));
                        request.setAttribute("numberOfFollowings",FollowerService.getInstance().getNumberOfFollowings(user.getId()));
                        request.setAttribute("fotoMap",FotoService.getInstance().getFotoInformationMap(user.getId(),user.getId()));
                        page = PathManager.getProperty("common.userAccountPage");
                    } else {
                        request.setAttribute("errorMassage", PageContentManager.getProperty("error.registration.token2"));
                        page = PathManager.getProperty("common.loginPage");
                    }
                } else {
                    request.setAttribute("errorMassage", PageContentManager.getProperty("error.loginCommandLoginError"));
                    page = PathManager.getProperty("common.loginPage");
                }

            }catch (ServiceException e){
                LOGGER.info("Exception in LoginCommand", e);
                request.setAttribute("errorMassage", PageContentManager.getProperty("error.loginCommandUnknownError"));
                page = PathManager.getProperty("common.loginPage");
            }

            return page;
        }
    }

