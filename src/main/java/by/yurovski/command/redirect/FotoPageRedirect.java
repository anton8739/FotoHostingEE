package by.yurovski.command.redirect;

import by.yurovski.command.Command;
import by.yurovski.entity.Foto;
import by.yurovski.entity.LikeFoto;
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

public class FotoPageRedirect implements Command {
    private static final Logger LOGGER = Logger.getLogger(FotoPageRedirect.class);
    private static Command instance = new FotoPageRedirect();
    private FotoPageRedirect  (){}
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
        String fotoName=request.getParameter("foto");
        Foto foto=FotoService.getInstance().getFotoByName(fotoName);
        request.setAttribute("currentUser", user);
        request.setAttribute("numberOfLikes", UserService.getInstance().getNamberOfLikeOfAllUsersFoto(user));
        request.setAttribute("numberOfFotos", FotoService.getInstance().getNumberOfFotoOfCurrentUser(user));
        request.setAttribute("foto",foto);

        if (LikeFotoService.getInstance().isLikedByCurrentUser(currentUserId,foto.getId())!=null){
            request.setAttribute("isLikedFoto",1);
        }
        request.setAttribute("numberOfFotoLike",LikeFotoService.getInstance().getNumberOfLikesByFotoId(foto.getId()));
        request.setAttribute("commentMap",CommentService.getInstance().getCommentInformationMap(foto.getId(),user.getId(),loginedUserId));
    } catch (ServiceException e){
        LOGGER.info("Exception in FotoPageRedirectCommand", e);
    }
        return PathManager.getProperty("foto.fotoPage");
    }
}
