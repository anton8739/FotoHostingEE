package by.yurovski.command.foto;


import by.yurovski.command.Command;
import by.yurovski.command.redirect.EmptyCommand;
import by.yurovski.command.redirect.UserAccountRedirectCommand;
import by.yurovski.entity.Foto;
import by.yurovski.entity.User;
import by.yurovski.enums.UserStatusEnum;
import by.yurovski.exception.ServiceException;
import by.yurovski.manager.PathManager;
import by.yurovski.service.FollowerService;
import by.yurovski.service.FotoService;
import by.yurovski.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class SortFotoCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(SortFotoCommand.class);
    private static Command instance = new SortFotoCommand();
    private SortFotoCommand (){}
    public static Command getInstance() {
        return instance;
    }

    @Override
    public String execute(HttpServletRequest request) {
        if (request.getSession().getAttribute("status")==null){
            return EmptyCommand.getInstance().execute(request);
        }
        int currentUserId;
        int loginedUserId=(int) request.getSession().getAttribute("id");

        if (request.getParameter("userId") == null){
            currentUserId=loginedUserId;
        }else{
            currentUserId=Integer.parseInt(request.getParameter("userId"));
        }
        String sort = request.getParameter("sort");



        try {
            User user=UserService.getInstance().getUserById(currentUserId);
            LinkedHashMap<Foto,List<Integer>> map=FotoService.getInstance().getFotoInformationMap(user.getId(),loginedUserId);
            LinkedHashMap<Foto,List<Integer>> sorted;
            if(sort.equals("old")){
                Comparator<Foto> comparator= (o1,o2) -> {
                    if(o1.getTimeOfCreation().getTime()<o2.getTimeOfCreation().getTime()){
                        return -1;
                    } else if (o1.getTimeOfCreation().getTime()==o2.getTimeOfCreation().getTime()){
                        return 0;
                    } else {
                        return 1;
                    }
                };
                sorted= new LinkedHashMap<Foto,List<Integer>>();
                map.entrySet().stream()
                        .sorted(Map.Entry.comparingByKey(comparator))
                        .forEachOrdered(x -> sorted.put(x.getKey(), x.getValue()));
            } else if (sort.equals("new")){
                Comparator<Foto> comparator= (o1,o2) -> {
                    if(o1.getTimeOfCreation().getTime()>o2.getTimeOfCreation().getTime()){
                        return -1;
                    } else if (o1.getTimeOfCreation().getTime()==o2.getTimeOfCreation().getTime()){
                        return 0;
                    } else {
                        return 1;
                    }
                };
                sorted= new LinkedHashMap<Foto,List<Integer>>();
                map.entrySet().stream()
                        .sorted(Map.Entry.comparingByKey(comparator))
                        .forEachOrdered(x -> sorted.put(x.getKey(), x.getValue()));
            } else if(sort.equals("pop")){
                Comparator<List<Integer>> comparator = (o1,o2) -> {
                    if (o1.get(0) < o2.get(0)){
                        return 1;
                    } else if (o1.get(0)==o2.get(0)){
                        return 0;
                    } else {
                        return -1;
                    }
                };
                sorted= new LinkedHashMap<Foto,List<Integer>>();
                map.entrySet().stream()
                        .sorted(Map.Entry.comparingByValue(comparator))
                        .forEachOrdered(x -> sorted.put(x.getKey(), x.getValue()));
            } else {
                sorted= new LinkedHashMap<Foto,List<Integer>>();
                sorted.putAll(map);
            }

            request.setAttribute("currentUser", user);
            request.setAttribute("numberOfLikes", UserService.getInstance().getNamberOfLikeOfAllUsersFoto(user));
            request.setAttribute("numberOfFotos", FotoService.getInstance().getNumberOfFotoOfCurrentUser(user));
            request.setAttribute("numberOfFollowers",FollowerService.getInstance().getNumberOfFollowers(user.getId()));
            request.setAttribute("numberOfFollowings",FollowerService.getInstance().getNumberOfFollowings(user.getId()));
            request.setAttribute("fotoMap",sorted);

        }  catch (ServiceException e){
        LOGGER.info("Exception in SortFotoCommand", e);
    }

        return PathManager.getProperty("common.userAccountPage");
    }
}
