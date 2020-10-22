package by.yurovski.command.user;


import by.yurovski.command.Command;
import by.yurovski.command.foto.LikeImgCommand;
import by.yurovski.command.redirect.EmptyCommand;
import by.yurovski.entity.Follower;
import by.yurovski.entity.LikeFoto;
import by.yurovski.entity.User;
import by.yurovski.enums.UserStatusEnum;
import by.yurovski.exception.ServiceException;
import by.yurovski.service.FollowerService;
import by.yurovski.service.LikeFotoService;
import by.yurovski.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class FollowCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(FollowCommand.class);
    private static Command instance = new FollowCommand();
    private FollowCommand(){}
    public static Command getInstance() {
        return instance;
    }
    @Override
    public void executeJSON(HttpServletRequest request, HttpServletResponse response) {
        int userId=Integer.parseInt(request.getParameter("userId"));
        int currnetUserId=Integer.parseInt(request.getParameter("currentUserId"));
        System.out.println(userId);
        System.out.println(currnetUserId);
        String JSONAnswer;
        int numberOfFollowings=0;
        int isFollowed=0;
        try {
            User follower=UserService.getInstance().getUserById(currnetUserId);
            User following=UserService.getInstance().getUserById(userId);
            Follower fol=FollowerService.getInstance().isFollowed(follower,following);
            if (fol !=null){
                FollowerService.getInstance().delete(fol);
                System.out.println("true");
                isFollowed=0;
            } else {
                fol=new Follower(currnetUserId,userId);
                FollowerService.getInstance().save(fol);
                System.out.println("false");
                isFollowed=1;
            }
            numberOfFollowings=FollowerService.getInstance().getNumberOfFollowings(follower.getId());
        }catch (ServiceException e){
            LOGGER.info(" method LikeImgCommand ServiceExceptoin error");
        }
        if(true){
            JSONAnswer="{\"isFollowed\" : "+isFollowed+", \"numberOfFollowings\" :"+numberOfFollowings+"}";
            System.out.println(JSONAnswer);
        }

        try {
            PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print(JSONAnswer);
            out.flush();
        } catch (IOException e){
            LOGGER.info(" LikeImgCommand failed to send JSON");
        }
    }
    }

