package by.yurovski.service;

import by.yurovski.dao.FollowerDao;
import by.yurovski.dao.FotoDao;
import by.yurovski.dao.UserDao;
import by.yurovski.dao.pool.ConnectionPool;
import by.yurovski.dao.util.ConnectionUtil;
import by.yurovski.entity.Follower;
import by.yurovski.entity.User;
import by.yurovski.exception.DaoException;
import by.yurovski.exception.ServiceException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FollowerService {
    private static FollowerService instance = new FollowerService();
    private FollowerService(){}
    public static FollowerService getInstance (){
        return instance;
    }

    /**
     * Insert follower into database
     * @param follower
     * @return auto incremented id
     * @throws ServiceException
     */
    public int save(Follower follower) throws ServiceException{
        try {
            return FollowerDao.getInstance().save(follower);
        } catch (DaoException e) {
            throw new ServiceException("Exception in FollowerService.save()", e);
        }
    }
    /**
     * Delete follower from database
     * @param follower
     * @return void
     * @throws ServiceException
     */
    public void delete(Follower follower) throws ServiceException{
        try {
            FollowerDao.getInstance().delete(follower);
        } catch (DaoException e) {
            throw new ServiceException("Exception in FollowerService.delete()", e);
        }
    }
    /**
     * Select all followings of current user from database.
     * @param userId
     * @return List<User> if the match was found and null otherwise
     * @throws ServiceException
     */
    public List<User> getFollowings(int userId) throws ServiceException {
        try {
            List<User> userList= new ArrayList<>();
            List<Integer> list=FollowerDao.getInstance().getFollowingsIDs(userId);
            for (Integer integer:list){
                userList.add(UserDao.getInstance().getUserById(integer));
            }
            return userList;

        } catch (DaoException e) {
            throw new ServiceException("Exception in FollowerService.getFollowings()", e);
        }
    }
    /**
     * Select all followers of current user from database.
     * @param userId
     * @return List<User> if the match was found and null otherwise
     * @throws ServiceException
     */
    public List<User> getFollowers(int userId) throws ServiceException {
        try {
            List<User> userList= new ArrayList<>();
            List<Integer> list=FollowerDao.getInstance().getFollowersIDs(userId);
            for (Integer integer:list){
                userList.add(UserDao.getInstance().getUserById(integer));
            }
            return userList;
        } catch (DaoException e) {
            throw new ServiceException("Exception in FollowerService.getFollowers()", e);
        }
    }
    /**
     * Calculate number of followings of current user;
     * @param userId
     * @return int number of followings of current user.
     * @throws ServiceException
     */
    public  int getNumberOfFollowings(int userId) throws ServiceException{
        try {
        List<Integer> list=FollowerDao.getInstance().getFollowingsIDs(userId);
        return list.size();
        } catch (DaoException e) {
            throw new ServiceException("Exception in FollowerService.getNumberOfFollowings()", e);
        }
    }
    /**
     * Check if current user followed on user.
     * @param loginedUser,user
     * @return true if followed and false otherwise
     * @throws ServiceException
     */
    public Follower isFollowed(User loginedUser, User user) throws ServiceException{
        try {
            return FollowerDao.getInstance().isFollowed(loginedUser,user);
        } catch (DaoException e) {
            throw new ServiceException("Exception in FollowerService.isFollowed()", e);
        }
        }
    /**
     * Calculate number of follower of current user;
     * @param userId
     * @return int number of followings of current user
     * @throws ServiceException
     */
    public int getNumberOfFollowers(int userId) throws ServiceException{
        try {
        List<Integer> list=FollowerDao.getInstance().getFollowersIDs(userId);
        return  list.size();
        } catch (DaoException e) {
        throw new ServiceException("Exception in FollowerService.getNumberOfFollowers()", e);
    }
    }
}
