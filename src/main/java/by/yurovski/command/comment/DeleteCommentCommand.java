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

public class DeleteCommentCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(DeleteCommentCommand.class);
    private static Command instance = new DeleteCommentCommand();
    private DeleteCommentCommand (){}
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
        int commentId=Integer.parseInt(request.getParameter("commentId"));
        String fotoName=request.getParameter("foto");

        try {
            Foto foto=FotoService.getInstance().getFotoByName(fotoName);
            Comment comment=CommentService.getInstance().getCommentById(commentId);
            User user=UserService.getInstance().getUserById(currentUserId);
            CommentService.getInstance().delete(comment);
            request.setAttribute("currentUser", user);
            request.setAttribute("numberOfLikes", UserService.getInstance().getNamberOfLikeOfAllUsersFoto(user));
            request.setAttribute("numberOfFotos", FotoService.getInstance().getNumberOfFotoOfCurrentUser(user));
            request.setAttribute("foto",foto);
            if (LikeFotoService.getInstance().isLikedByCurrentUser(currentUserId,foto.getId())!=null){
                request.setAttribute("isLikedFoto",1);
            }
            request.setAttribute("numberOfFotoLike",LikeFotoService.getInstance().getNumberOfLikesByFotoId(foto.getId()));
            request.setAttribute("commentMap",CommentService.getInstance().getCommentInformationMap(foto.getId(),user.getId(),loginedUserId));
        }catch (ServiceException e){
            LOGGER.info(" method AddNewCommentCommand ServiceExceptoin error");
        }
        return PathManager.getProperty("foto.fotoPage");
    }

}
