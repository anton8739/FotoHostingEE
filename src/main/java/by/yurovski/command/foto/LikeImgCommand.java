package by.yurovski.command.foto;


import by.yurovski.command.Command;
import by.yurovski.command.redirect.EmptyCommand;
import by.yurovski.entity.LikeFoto;
import by.yurovski.enums.UserStatusEnum;
import by.yurovski.exception.ServiceException;
import by.yurovski.service.LikeFotoService;
import netscape.javascript.JSObject;
import org.apache.log4j.Logger;
import org.json.HTTP;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LikeImgCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(LikeImgCommand.class);
    private static Command instance = new LikeImgCommand();
    private LikeImgCommand (){}
    public static Command getInstance() {
        return instance;
    }

    @Override
    public void executeJSON(HttpServletRequest request, HttpServletResponse response) {
        int fotoId=Integer.parseInt(request.getParameter("fotoId"));
        int currnetUserId=Integer.parseInt(request.getParameter("userId"));
        String JSONAnswer;
        int numberOfLikes=0;
        int isLiked=0;
        try {
            LikeFoto likeFoto=LikeFotoService.getInstance().isLikedByCurrentUser(currnetUserId,fotoId);
            if (likeFoto !=null){
                LikeFotoService.getInstance().delete(likeFoto);
                System.out.println("true");
                isLiked=0;
            } else {
                likeFoto=new LikeFoto(currnetUserId,fotoId);
                LikeFotoService.getInstance().save(likeFoto);
                System.out.println("false");
                isLiked=1;
            }
            numberOfLikes=LikeFotoService.getInstance().getNumberOfLikesByFotoId(fotoId);


        }catch (ServiceException e){
            LOGGER.info(" method LikeImgCommand ServiceExceptoin error");
        }

        JSONAnswer="{\"numberOflikes\" : \""+numberOfLikes+"\", \"isLiked\" : "+isLiked+"}";


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
