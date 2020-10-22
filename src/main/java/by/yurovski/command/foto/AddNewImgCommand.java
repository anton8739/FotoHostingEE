package by.yurovski.command.foto;

import by.yurovski.command.Command;
import by.yurovski.command.redirect.EmptyCommand;
import by.yurovski.entity.Foto;
import by.yurovski.entity.User;
import by.yurovski.enums.UserStatusEnum;
import by.yurovski.exception.ServiceException;
import by.yurovski.manager.PathManager;
import by.yurovski.service.FollowerService;
import by.yurovski.service.FotoService;
import by.yurovski.service.UserService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;

public class AddNewImgCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(AddNewImgCommand.class);
    private static Command instance = new AddNewImgCommand();
    private AddNewImgCommand (){}
    public static Command getInstance() {
        return instance;
    }

    @Override
    public String execute(HttpServletRequest request) {
        if (request.getSession().getAttribute("status")==null){
            return EmptyCommand.getInstance().execute(request);
        }
        int userId=(int)request.getSession().getAttribute("id");
        User user = null;
        String destionationRealPath=request.getServletContext().getRealPath("/");
        destionationRealPath=destionationRealPath.substring(0,destionationRealPath.length()-2);
        destionationRealPath=destionationRealPath
                .substring(0, destionationRealPath.lastIndexOf('/'))+"/files/";

        System.out.println(destionationRealPath);
        DiskFileItemFactory factory=new DiskFileItemFactory();
        factory.setSizeThreshold(1024*1000);
        factory.setRepository(new File(destionationRealPath));
        ServletFileUpload uploader = new ServletFileUpload(factory);
        try {

            List items=uploader.parseRequest(request);
            Iterator iterator=items.iterator();
            File dir = new File(destionationRealPath);
            dir.mkdir();
            while (iterator.hasNext()){
                FileItem item=(FileItem) iterator.next();
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                String fotoName=hashName(item.getName(),timestamp);
                System.out.println(fotoName);

                String fotoURL="/files/"+fotoName+item.getName().substring(item.getName().lastIndexOf("."));
                System.out.println(fotoURL);
                Foto foto=new Foto(fotoName,fotoURL, userId, timestamp);
                FotoService.getInstance().save(foto);
                String filePath=destionationRealPath+fotoName+item.getName().substring(item.getName().lastIndexOf("."));
                System.out.println(destionationRealPath);
                File file = new File(filePath);
                item.write(file);

            }
        }catch (ServiceException e){
            LOGGER.info(" method getPage() ServiceExceptoin error");
        }catch (Exception e){
            LOGGER.info(" method getPage() Unknown error");
        }
        return null;
    }
    public static String hashName(String name, Timestamp timestamp){


        String hashName=name+timestamp.getTime();

        String realName="foto"+hashName.hashCode();
        return realName;
    }
}

