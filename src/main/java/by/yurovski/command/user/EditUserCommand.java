package by.yurovski.command.user;

import by.yurovski.command.Command;
import by.yurovski.entity.User;
import by.yurovski.exception.RegistrationException;
import by.yurovski.exception.ServiceException;
import by.yurovski.manager.PageContentManager;
import by.yurovski.service.UserService;
import by.yurovski.validation.ValidationUtil;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class EditUserCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(EditUserCommand.class);
    private static Command instance = new EditUserCommand();
    private EditUserCommand(){}
    public static Command getInstance() {
        return instance;
    }

    @Override
    public void executeJSON(HttpServletRequest request, HttpServletResponse response) {
        int userId=Integer.parseInt(request.getSession().getAttribute("id").toString());
        String userName=request.getParameter("userName");
        String userSurname=request.getParameter("userSurname");
        String userEmail=request.getParameter("userEmail");
        String userLogin=request.getParameter("userLogin");
        String JSONAnswer=null;
        User user=null;
        try {
            user=UserService.getInstance().getUserById(userId);
            user.setName(userName);
            user.setSurname(userSurname);
            user.setLogin(userLogin);
            user.setEmail(userEmail);
            ValidationUtil.editValidator(user);
            UserService.getInstance().update(user);
            request.getSession().setAttribute("status", user.getStatus());
            request.getSession().setAttribute("user", user.getLogin());
            request.getSession().setAttribute("id", user.getId());
            request.getSession().setAttribute("loginedUser", user);
            JSONAnswer="{\"login\" : \""+user.getLogin()+"\", \"errorMessage\" : \""+PageContentManager.getProperty("success.edit")+"\"}";
        } catch (ServiceException e){
            LOGGER.info("EditUserCommand",e);
        } catch (RegistrationException e){
            JSONAnswer="{\"login\" : \""+user.getLogin()+"\", \"errorMessage\" : \""+e.getMessage()+"\"}";
        }
        System.out.println(JSONAnswer);
        try {
            response.setContentType("application/json;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.print(JSONAnswer);
            out.flush();
        } catch (IOException e){
            LOGGER.info(" LikeImgCommand failed to send JSON");
        }

    }

}
