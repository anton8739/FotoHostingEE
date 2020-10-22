package by.yurovski.command.user;

import by.yurovski.command.Command;
import by.yurovski.emailVerification.EmailSenderForgotPassword;
import by.yurovski.entity.User;
import by.yurovski.exception.ServiceException;
import by.yurovski.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ForgotPasswordCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(ForgotPasswordCommand.class);
    private static ForgotPasswordCommand instance = new ForgotPasswordCommand();
    private ForgotPasswordCommand(){}
    public static ForgotPasswordCommand getInstance() {
        return instance;
    }
    @Override
    public void executeJSON(HttpServletRequest request, HttpServletResponse response) {

        String loginOrEmail=request.getParameter("login-email");


        System.out.println(loginOrEmail);
        User user=null;
        String JSONAnswer;
        try {
            if (loginOrEmail.indexOf('@')!=(-1)){
                user=UserService.getInstance().getUserByEmail(loginOrEmail);
            } else {
                user=UserService.getInstance().getUserByLogin(loginOrEmail);
            }
        } catch (ServiceException e){
            LOGGER.info(" method LikeImgCommand ForgotPasswordCommand error");
        }
        if(user==null){
            JSONAnswer="{\"status\" : \"Email not sent - no such user\"}";
        } else {
            JSONAnswer="{\"status\" : \"Email sent successfully\"}";
            EmailSenderForgotPassword.sendMessage(user);
            }

        try {
                PrintWriter out = response.getWriter();
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                out.print(JSONAnswer);
                out.flush();
        } catch (IOException e) {
            LOGGER.info(" LikeImgCommand failed to send JSON");
        }





    }
}
