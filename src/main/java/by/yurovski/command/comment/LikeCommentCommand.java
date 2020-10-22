package by.yurovski.command.comment;

import by.yurovski.command.Command;
import by.yurovski.command.foto.LikeImgCommand;
import by.yurovski.command.redirect.EmptyCommand;
import by.yurovski.entity.LikeComment;
import by.yurovski.entity.LikeFoto;
import by.yurovski.enums.UserStatusEnum;
import by.yurovski.exception.ServiceException;
import by.yurovski.service.LikeCommentService;
import by.yurovski.service.LikeFotoService;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LikeCommentCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(LikeCommentCommand.class);
    private static Command instance = new LikeCommentCommand();
    private LikeCommentCommand (){}
    public static Command getInstance() {
        return instance;
    }

    @Override
    public void executeJSON(HttpServletRequest request, HttpServletResponse response) {
        int commentId=Integer.parseInt(request.getParameter("commentId"));
        int currnetUserId=Integer.parseInt(request.getParameter("userId"));
        System.out.println(commentId+" "+ currnetUserId);
        String JSONAnswer;
        int numberOfLikes=0;
        int isLiked=0;
        try {
            LikeComment likeComment=LikeCommentService.getInstance().isLikedByCurrentUser(currnetUserId,commentId);
            if (likeComment !=null){
                LikeCommentService.getInstance().delete(likeComment);
                System.out.println("true");
                isLiked=0;
            } else {
                likeComment=new LikeComment(currnetUserId,commentId);
                LikeCommentService.getInstance().save(likeComment);
                System.out.println("false");
                isLiked=1;
            }
            numberOfLikes=LikeCommentService.getInstance().getNumberOfLikesByCommentId(commentId);


        }catch (ServiceException e){
            LOGGER.info(" method LikeImgCommand ServiceExceptoin error");
        }
        if(true){
            JSONAnswer="{\"numberOflikes\" : \""+numberOfLikes+"\", \"isLiked\" : "+isLiked+"}";
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
