package by.yurovski.command.comment;


import by.yurovski.command.Command;
import by.yurovski.command.redirect.EmptyCommand;
import by.yurovski.entity.Comment;
import by.yurovski.entity.Foto;
import by.yurovski.entity.User;
import by.yurovski.enums.UserStatusEnum;
import by.yurovski.exception.ServiceException;
import by.yurovski.manager.PathManager;
import by.yurovski.service.CommentService;
import by.yurovski.service.FotoService;
import by.yurovski.service.LikeFotoService;
import by.yurovski.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class AddNewCommentCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(AddNewCommentCommand.class);
    private static Command instance = new AddNewCommentCommand();
    private AddNewCommentCommand (){}
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
        }else {
            currentUserId = Integer.parseInt(request.getParameter("userId"));
        }
        String text=request.getParameter("text");
        String fotoName=request.getParameter("foto");

        String uId=request.getSession().getAttribute("id").toString();
        int userId=Integer.parseInt(uId);
        try {
            Foto foto=FotoService.getInstance().getFotoByName(fotoName);
            Comment comment=new Comment(text,userId,foto.getId());
            User user=UserService.getInstance().getUserById(currentUserId);
            CommentService.getInstance().save(comment);
        }catch (ServiceException e){
            LOGGER.info(" method AddNewCommentCommand ServiceExceptoin error");
        }
        return null;
    }
}
