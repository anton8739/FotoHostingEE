package by.yurovski.service;

import by.yurovski.dao.FotoDao;
import by.yurovski.dao.LikeFotoDao;
import by.yurovski.dao.pool.ConnectionPool;
import by.yurovski.dao.util.ConnectionUtil;
import by.yurovski.entity.LikeFoto;
import by.yurovski.exception.DaoException;
import by.yurovski.exception.ServiceException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LikeFotoService {
    private static LikeFotoService instance = new LikeFotoService();
    private LikeFotoService(){}
    public static LikeFotoService getInstance (){
        return instance;
    }
    /**
     * Insert like into database
     * @param likeFoto
     * @return auto incremented id
     * @throws ServiceException
     */
    public int save(LikeFoto likeFoto) throws ServiceException{
        try {
            return LikeFotoDao.getInstance().save(likeFoto);
        } catch (DaoException e){
            throw new ServiceException("Exception in save() ", e);
        }
    }
    /**
     * Delete likefoto from database
     * @param likeFoto
     * @return void
     * @throws ServiceException
     */
    public void delete(LikeFoto likeFoto) throws ServiceException{
        try {
            LikeFotoDao.getInstance().delete(likeFoto);
        } catch (DaoException e){
            throw new ServiceException("Exception in delete() ", e);
        }
    }
    /**
     * Get number of likes for current foto by foto id.
     * @param fotoId
     * * @return int  number of likes
     * @throws ServiceException
     */
    public int getNumberOfLikesByFotoId(int fotoId) throws ServiceException{
        try {
            return LikeFotoDao.getInstance().getNumberOfLikesByFotoId(fotoId);
        } catch (DaoException e){
            throw new ServiceException("Exception in isLikedByCurrentUser() ", e);
        }
    }
    /**
     * Checks whether foto is liked by current User
     * @param userId
     * * @return boolean true if is liked and false otherwise
     * @throws ServiceException
     */
    public LikeFoto isLikedByCurrentUser(int userId, int fotoId) throws ServiceException{
        try {
            return LikeFotoDao.getInstance().isLikedByCurrentUser(userId,fotoId);
        } catch (DaoException e){
            throw new ServiceException("Exception in isLikedByCurrentUser() ", e);
        }
    }
}
