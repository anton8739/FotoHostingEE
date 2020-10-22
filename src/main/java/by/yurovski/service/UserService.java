package by.yurovski.service;

import by.yurovski.dao.UserDao;
import by.yurovski.dao.pool.ConnectionPool;
import by.yurovski.dao.util.ConnectionUtil;
import by.yurovski.entity.Foto;
import by.yurovski.entity.User;
import by.yurovski.exception.DaoException;
import by.yurovski.exception.ServiceException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class UserService {

    private static UserService instance = new UserService();
    private UserService(){}
    public static UserService getInstance (){
        return instance;
    }
    /**
     * Insert user into database
     * @param user
     * @return auto incremented id
     * @throws ServiceException
     */
    public int save(User user) throws ServiceException{
        try {
            return UserDao.getInstance().save(user);
        } catch (DaoException e) {
            throw new ServiceException("Exception in UserService.save()", e);
        }
    }
    /**
     * Delete user from database
     * @param user
     * @return void
     * @throws ServiceException
     */
    public void delete(User user) throws ServiceException{
        try {
            UserDao.getInstance().delete(user);
        } catch (DaoException e) {
            throw new ServiceException("Exception in UserService.delete()", e);
        }

    }
    /**
     * Update user in database
     * @param user
     * @return void
     * @throws ServiceException
     */
    public void update(User user) throws ServiceException{
        try {
           UserDao.getInstance().update(user);
        } catch (DaoException e) {
            throw new ServiceException("Exception in UserService.update()", e);
        }
    }
    /**
     * Enable user
     * @param user
     * @throws ServiceException
     */
    public void enableUser(User user) throws ServiceException{
        try {
            UserDao.getInstance().enableUser(user);
        } catch (DaoException e) {
            throw new ServiceException("Exception in UserService.save()", e);
        }
    }
    /**
     * Checks whether the database contains user whit current login
     * @param userLogin
     * @return User object if the match was found and null otherwise
     * @throws ServiceException
     */
    public User getUserByLogin(String userLogin) throws ServiceException{
        try {
            return UserDao.getInstance().getUserByLogin(userLogin);
        } catch (DaoException e) {
            throw new ServiceException("Exception in UserService.getUserByLogin()", e);
        }
    }
    /**
     * Checks whether the database contains user whit current email
     * @param userEmail
     * @return User object if the match was found and null otherwise
     * @throws ServiceException
     */
    public User getUserByEmail(String userEmail) throws ServiceException{
        try {
            return UserDao.getInstance().getUserByEmail(userEmail);
        } catch (DaoException e) {
            throw new ServiceException("Exception in UserService.getUserByEmail()", e);
        }
    }
    /**
     * Find user whos name starts with parametr
     * @param partOfName
     * @return List<User> whos names starts with partOfName.
     * @throws ServiceException
     */
    public List<User> findUsersByPartOfName (String partOfName) throws ServiceException {
        try {
            List<User> userList=UserDao.getInstance().selectAll();
            List<User> newUserList=new ArrayList<>();
            for (User user:userList){
                if (user.getLogin().startsWith(partOfName)){
                    newUserList.add(user);
                }
            }
            return newUserList;
        } catch (DaoException e) {
            throw new ServiceException("Exception in UserServiceImpl.checkLoginPassword()", e);
        }
    }
    /**
     * Checks whether the database contains a pair of login and password
     * @param login
     * @param password
     * @return User object if the match was found and null otherwise
     * @throws ServiceException
     */
    public User checkLoginPassword (String login, String password) throws ServiceException {
        try {
            return UserDao.getInstance().checkLoginPassword(login, password);
        } catch (DaoException e) {
            throw new ServiceException("Exception in UserServiceImpl.checkLoginPassword()", e);
        }
    }
    /**
     * Checks whether the database contains user whit current login
     * @param userId
     * @return User object if the match was found and null otherwise
     * @throws ServiceException
     */
    public User  getUserById(int userId) throws ServiceException {
        try {
            return UserDao.getInstance().getUserById(userId);
        } catch (DaoException e) {
            throw new ServiceException("Exception in UserService.getUserByLogin()", e);
        }
    }
    /**
     * Get number of likes of all user's foto.
     * @param user
     * @return int number of likes.
     * @throws ServiceException
     */
    public int getNamberOfLikeOfAllUsersFoto(User user) throws ServiceException{
            int numberOfLikes=0;
            List<Foto> fotos=FotoService.getInstance().getFotosByUserID(user.getId());
            for (Foto foto:fotos){
                numberOfLikes += LikeFotoService.getInstance().getNumberOfLikesByFotoId(foto.getId());
            }
            return numberOfLikes;
    }
    /**
     * Get all followers of currentUser and status of them(subscribed/unsubscribed).
     * @param user
     * @return LinkedHashMap<User,Integer> all followers of currentUser and status of them.
     * @throws ServiceException
     */
    public LinkedHashMap<User,Integer> getFollowersInformationMap(User loginedUser,User user) throws ServiceException{
        LinkedHashMap<User,Integer> map=new LinkedHashMap<>();
        List<User> userList=FollowerService.getInstance().getFollowers(user.getId());
        for(User us:userList){
            if (FollowerService.getInstance().isFollowed(loginedUser,us)!=null){
                map.put(us, 1);
            } else {
                map.put(us, 0);
            }
        }
        return map;
    }
    /**
     * Get all followings of currentUser and status of them(subscribed/unsubscribed).
     * @param user
     * @return LinkedHashMap<User,Integer> all followings of currentUser and status of them.
     * @throws ServiceException
     */
    public LinkedHashMap<User,Integer> getFolloweingsInformationMap(User loginedUser,User user) throws ServiceException{
        LinkedHashMap<User,Integer> map=new LinkedHashMap<>();
        List<User> userList=FollowerService.getInstance().getFollowings(user.getId());
        for(User us:userList){
            if (FollowerService.getInstance().isFollowed(loginedUser,us)!=null){
                map.put(us, 1);
            } else {
                map.put(us, 0);
            }
        }
        return map;
    }
    /**
     * Get all followings of currentUser and status of them(subscribed/unsubscribed).
     * @param user
     * @return LinkedHashMap<User,Integer> all followings of currentUser and status of them.
     * @throws ServiceException
     */
    public LinkedHashMap<User,Integer> getFoundedUsersInformationMap(User loginedUser,User user,String partOfName) throws ServiceException{
        LinkedHashMap<User,Integer> map=new LinkedHashMap<>();
        List<User> userList=findUsersByPartOfName(partOfName);
        for(User us:userList){
            if (FollowerService.getInstance().isFollowed(loginedUser,us)!=null){
                map.put(us, 1);
            } else {
                map.put(us, 0);
            }
        }
        return map;
    }

}
