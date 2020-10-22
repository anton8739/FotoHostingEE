package by.yurovski.command.user;


import by.yurovski.command.Command;
import by.yurovski.command.redirect.UserAccountRedirectCommand;
import by.yurovski.dao.UserVerificationTokenDao;
import by.yurovski.emailVerification.EmailSender;
import by.yurovski.entity.User;
import by.yurovski.entity.UserVerificationToken;
import by.yurovski.exception.RegistrationException;
import by.yurovski.exception.ServiceException;
import by.yurovski.manager.PageContentManager;
import by.yurovski.manager.PathManager;
import by.yurovski.service.FollowerService;
import by.yurovski.service.FotoService;
import by.yurovski.service.UserService;
import by.yurovski.service.UserVerificationTokenService;
import by.yurovski.validation.ValidationUtil;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;

public class RegistrationCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(RegistrationCommand.class);
    private static Command instance = new RegistrationCommand();
    private RegistrationCommand(){}
    public static Command getInstance() {
        return instance;
    }
    @Override
    public String execute(HttpServletRequest request) {
        if (request.getSession().getAttribute("user")!=null){
            return UserAccountRedirectCommand.getInstance().execute(request);
        }
        String login=request.getParameter("login");
        String password=request.getParameter("password");
        String email=request.getParameter("email");
        String token=request.getParameter("token");
        try {

            User user=null;
            if (token!=null){

                user=ValidationUtil.checkToken(token);
            }
            if (user!=null){

                UserService.getInstance().enableUser(user);
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
                return PathManager.getProperty("common.userAccountPage");
            }
            if (request.getParameter("checkBoxRules") ==null){
                throw new RegistrationException(PageContentManager.getProperty("error.registration.rules"));
            }
            user=new User(email,login,password);
            ValidationUtil.registrationValidator(user);
            int userId=UserService.getInstance().save(user);
            System.out.println(userId);
            user.setId(userId);
            System.out.println("get"+user.getId());
            token = UUID.randomUUID().toString();
            UserVerificationToken userVerificationToken=new UserVerificationToken(user,token,new Date());
            UserVerificationTokenService.getInstance().save(userVerificationToken);
            EmailSender.sendMessage(user,userVerificationToken, request.getContextPath());
            return PathManager.getProperty("common.main");

        } catch (ServiceException e){
            LOGGER.info("ServiceException in RegistrationCommand", e);
            request.setAttribute("errorMassage", PageContentManager.getProperty("error.loginCommandUnknownError"));
            return PathManager.getProperty("common.registrationPage");
        }
        catch (RegistrationException e){
            LOGGER.info("Exception in RegistrationCommand", e);
            request.setAttribute("errorMassage", e.getMessage());
            return PathManager.getProperty("common.registrationPage");
        }
    }
}
