package by.yurovski.service;

import by.yurovski.dao.UserDao;
import by.yurovski.dao.UserVerificationTokenDao;
import by.yurovski.dao.pool.ConnectionPool;
import by.yurovski.dao.util.ConnectionUtil;
import by.yurovski.entity.UserVerificationToken;
import by.yurovski.exception.DaoException;
import by.yurovski.exception.ServiceException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserVerificationTokenService {
    private static UserVerificationTokenService instance = new UserVerificationTokenService();
    private UserVerificationTokenService(){}
    public static UserVerificationTokenService getInstance (){
        return instance;
    }
    /**
     * Insert token into database
     * @param token
     * @return auto incremented id
     * @throws DaoException
     */
    public int save(UserVerificationToken token) throws ServiceException{
        try {
            return UserVerificationTokenDao.getInstance().save(token);
        } catch (DaoException e) {
            throw new ServiceException("Exception in UserVerificationTokenService.save()", e);
        }
    }
    /**
     * Delete token from database
     * @param token
     * @return auto incremented id
     * @throws DaoException
     */
    public void delete(UserVerificationToken token) throws ServiceException{
        try {
            UserVerificationTokenDao.getInstance().delete(token);
        } catch (DaoException e) {
            throw new ServiceException("Exception in UserVerificationTokenService.delete()", e);
        }
    }
    /**
     * Delete token from database
     * @param token
     * @return auto incremented id
     * @throws DaoException
     */
    public UserVerificationToken getTokenByToken(String token) throws ServiceException{
        try {
            return UserVerificationTokenDao.getInstance().getTokenByToken(token);
        } catch (DaoException e) {
            throw new ServiceException("Exception in UserVerificationTokenService.getTokenByToken()", e);
        }
    }

}
