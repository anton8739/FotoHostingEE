package by.yurovski.service;

import by.yurovski.dao.LikeCommentDao;
import by.yurovski.dao.LikeFotoDao;
import by.yurovski.dao.pool.ConnectionPool;
import by.yurovski.dao.util.ConnectionUtil;
import by.yurovski.entity.LikeComment;
import by.yurovski.exception.DaoException;
import by.yurovski.exception.ServiceException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LikeCommentService {
    private static LikeCommentService instance = new LikeCommentService();
    private LikeCommentService(){}
    public static LikeCommentService getInstance (){
        return instance;
    }
    /**
     * Insert like into database
     * @param likeComment
     * @return auto incremented id
     * @throws ServiceException
     */
    public int save(LikeComment likeComment) throws ServiceException{
        try {
            return LikeCommentDao.getInstance().save(likeComment);
        } catch (DaoException e){
            throw new ServiceException("Exception in save() ", e);
        }
    }
    /**
     * Delete likecomment from database
     * @param likeComment
     * @return void
     * @throws ServiceException
     */
    public void delete(LikeComment likeComment) throws ServiceException{
        try {
            LikeCommentDao.getInstance().delete(likeComment);
        } catch (DaoException e){
            throw new ServiceException("Exception in delete() ", e);
        }
    }
    /**
     * Get number of likes for current comment by comment id.
     * @param commentId
     * * @return int  number of likes
     * @throws ServiceException
     */
    public int getNumberOfLikesByCommentId(int commentId) throws ServiceException{
        try {
            return LikeCommentDao.getInstance().getNumberOfLikesByCommentId(commentId);
        } catch (DaoException e){
            throw new ServiceException("Exception in isLikedByCurrentUser() ", e);
        }
    }
    /**
     * Checks whether comment is liked by current User
     * @param userId,commentId
     * * @return boolean true if is liked and false otherwise
     * @throws ServiceException
     */
    public LikeComment isLikedByCurrentUser(int userId, int commentId) throws ServiceException{
        try {
            return LikeCommentDao.getInstance().isLikedByCurrentUser(userId,commentId);
        } catch (DaoException e){
            throw new ServiceException("Exception in isLikedByCurrentUser() ", e);
        }
    }
}
