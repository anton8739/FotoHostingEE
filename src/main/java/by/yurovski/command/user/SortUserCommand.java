package by.yurovski.command.user;

import by.yurovski.command.Command;
import by.yurovski.command.redirect.EmptyCommand;
import by.yurovski.command.redirect.UserAccountRedirectCommand;
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
import java.util.Map;

public class SortUserCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(SortUserCommand.class);
    private static Command instance = new SortUserCommand();
    private SortUserCommand(){}
    public static Command getInstance() {
        return instance;
    }
    @Override
    public String execute(HttpServletRequest request) {
        if (request.getSession().getAttribute("status")==null ||
                request.getSession().getAttribute("status")==UserStatusEnum.GUEST){
            return EmptyCommand.getInstance().execute(request);
        }
        String page=PathManager.getProperty("common.userList");
        int currentUserId;
        int loginedUserId=(int) request.getSession().getAttribute("id");
        if (request.getParameter("userId") == null){
            currentUserId=loginedUserId;
        }else{
            currentUserId=Integer.parseInt(request.getParameter("userId"));
        }
        try {
            User user = UserService.getInstance().getUserById(currentUserId);
            User loginedUser = UserService.getInstance().getUserById(loginedUserId);
            String partOfName=request.getParameter("part");
            request.setAttribute("partOfName",partOfName);
            request.setAttribute("currentUser", user);
            request.setAttribute("numberOfLikes", UserService.getInstance().getNamberOfLikeOfAllUsersFoto(user));
            request.setAttribute("numberOfFotos", FotoService.getInstance().getNumberOfFotoOfCurrentUser(user));
            request.setAttribute("numberOfFollowers", FollowerService.getInstance().getNumberOfFollowers(user.getId()));
            request.setAttribute("numberOfFollowings", FollowerService.getInstance().getNumberOfFollowings(user.getId()));
            String sort=request.getParameter("sort");
            String fromWhichPage=request.getParameter("from");
            LinkedHashMap<User,Integer> map;

            if (fromWhichPage.equals("followers")) {
                map=UserService.getInstance().getFollowersInformationMap(loginedUser,user);
                page=PathManager.getProperty("common.followers");
            } else if(fromWhichPage.equals("followings")){
                map=UserService.getInstance().getFolloweingsInformationMap(loginedUser,user);
                page=PathManager.getProperty("common.followings");
            } else {
                map=UserService.getInstance().getFoundedUsersInformationMap(loginedUser,user,partOfName);
                page=PathManager.getProperty("common.userList");
            }
            LinkedHashMap<User,Integer> sorted;
            if ("old".equals(sort)){
                sorted= new LinkedHashMap<>();
                Comparator<User> comparator = (o1, o2) -> {
                    if(o1.getTimeOfRegistration().getTime() < o2.getTimeOfRegistration().getTime()){
                        return 1;
                    } else if (o1.getTimeOfRegistration().getTime()==o2.getTimeOfRegistration().getTime()){
                        return 0;
                    } else {
                        return -1;
                    }
                };
                map.entrySet().stream()
                        .sorted(Map.Entry.comparingByKey(comparator))
                        .forEachOrdered(x -> sorted.put(x.getKey(), x.getValue()));

            } else if ("new".equals(sort)) {
                sorted = new LinkedHashMap<>();
                Comparator<User> comparator = (o1, o2) -> {
                    if (o1.getTimeOfRegistration().getTime() > o2.getTimeOfRegistration().getTime()) {
                        return 1;
                    } else if (o1.getTimeOfRegistration().getTime() == o2.getTimeOfRegistration().getTime()) {
                        return 0;
                    } else {
                        return -1;
                    }
                };
                map.entrySet().stream()
                        .sorted(Map.Entry.comparingByKey(comparator))
                        .forEachOrdered(x -> sorted.put(x.getKey(), x.getValue()));

            } else {
                sorted = new LinkedHashMap<>();
                sorted.putAll(map);

            }
            if (fromWhichPage.equals("followers")) {
               request.setAttribute("followersMap",sorted);
            } else if(fromWhichPage.equals("followings")){
                request.setAttribute("followingsMap",sorted);
            } else {
                request.setAttribute("usersMap",sorted);
            }
        }catch (ServiceException e){
            LOGGER.info("Exception in SortUserCommand", e);
        }
        return page;
    }
}
