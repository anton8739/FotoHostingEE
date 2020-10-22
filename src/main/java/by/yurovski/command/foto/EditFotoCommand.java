package by.yurovski.command.foto;

import by.yurovski.command.Command;
import by.yurovski.command.user.DeleteAccountCommand;
import by.yurovski.entity.Foto;
import by.yurovski.exception.ServiceException;
import by.yurovski.service.FotoService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class EditFotoCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(EditFotoCommand.class);
    private static Command instance = new EditFotoCommand();
    private EditFotoCommand (){}
    public static Command getInstance() {
        return instance;
    }

    @Override
    public void executeJSON(HttpServletRequest request, HttpServletResponse response) {
        int fotoId=Integer.parseInt(request.getParameter("fotoId"));
        String title=request.getParameter("title");
        String description=request.getParameter("description");
        try {
            Foto foto=FotoService.getInstance().getFotoById(fotoId);
            foto.setTitle(title);
            foto.setText(description);

            FotoService.getInstance().setTitleAndDescription(foto);

        } catch (ServiceException e){
            LOGGER.info(" method EditFotoCommand ServiceExceptoin error",e.getCause());
        }

        String JSONAnswer="{\"title\" : \""+title+"\", \"description\" : \""+description+"\"}";
        System.out.println(JSONAnswer);

        try {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();

            out.print(JSONAnswer);
            out.flush();
        } catch (IOException e){
            LOGGER.info(" LikeImgCommand failed to send JSON");
        }
    }
}
