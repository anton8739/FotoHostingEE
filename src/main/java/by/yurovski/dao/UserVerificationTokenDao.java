package by.yurovski.dao;


import by.yurovski.dao.pool.ConnectionPool;
import by.yurovski.dao.util.ConnectionUtil;
import by.yurovski.entity.Foto;
import by.yurovski.entity.User;
import by.yurovski.entity.UserVerificationToken;
import by.yurovski.enums.UserStatusEnum;
import by.yurovski.exception.DaoException;

import java.sql.*;
import java.util.Date;

public class UserVerificationTokenDao {
    private static UserVerificationTokenDao  instance = new UserVerificationTokenDao ();
    private UserVerificationTokenDao  (){}
    public static UserVerificationTokenDao  getInstance (){
        return instance;
    }

    private static final String INSERT_TOKEN = "INSERT INTO \"userverificationtoken\" (token,expirydate,user_id) VALUES " +
            "(?, ?, ?)";
    private static final String  DELETE_TOKEN = "DELETE FROM \"userverificationtoken\" WHERE token=?";
    private static final String  SELECT_TOKEN = "SELECT * FROM \"userverificationtoken\" WHERE token=?";
    /**
     * Insert token into database
     * @param token
     * @return auto incremented id
     * @throws DaoException
     */
    public int save(UserVerificationToken token) throws DaoException{
        int tokenId = -1;
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(INSERT_TOKEN,
                PreparedStatement.RETURN_GENERATED_KEYS)) {
            connection.setAutoCommit(false);
            statement.setString(1, token.getToken());
            statement.setLong(2, token.getExpiryDate());
            statement.setInt(3, token.getUserId());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()){
                tokenId = resultSet.getInt(1);
            }

            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            ConnectionUtil.rollbackConnection(connection, "UserVerificationToken.save(Foto foto)");
            throw new DaoException("UserVerificationToken.save(Foto foto)", e);

        } finally {
            ConnectionUtil.closeConnection(connection, "UserVerificationToken.save(Foto foto)");
        }
        return tokenId;
    }
    /**
     * Delete token from database
     * @param token
     * @return auto incremented id
     * @throws DaoException
     */
    public void delete(UserVerificationToken token) throws DaoException{
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(DELETE_TOKEN)) {
            connection.setAutoCommit(false);
            statement.setString(1, token.getToken());
            statement.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            ConnectionUtil.rollbackConnection(connection, "UserVerificationTokenDao.delete(UserVerificationToken token)");
            throw new DaoException("UserVerificationTokenDao.delete(UserVerificationToken token)", e);
        } finally {
            ConnectionUtil.closeConnection(connection, "UserVerificationTokenDao.delete(UserVerificationToken token)");
        }
    }
    /**
     * Delete token from database
     * @param token
     * @return auto incremented id
     * @throws DaoException
     */
    public UserVerificationToken getTokenByToken(String token) throws DaoException{
        UserVerificationToken userVerificationToken =null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_TOKEN)){
            statement.setString(1, token);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                userVerificationToken = getUserVerificationTokenFromResultSet(resultSet);
            }
            resultSet.close();
        } catch (SQLException e){
            throw  new DaoException("Exception in UserDao.getUserById()", e);
        }
        return userVerificationToken;
    }
    /**
     * @param resultSet
     * @return UserVerificationToken selected from resultSet
     * @throws SQLException
     */
    private UserVerificationToken getUserVerificationTokenFromResultSet (ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String token = resultSet.getString("token");
        Long expirydate = resultSet.getLong("expirydate");
        int userId = resultSet.getInt("user_id");

        return new UserVerificationToken (id,token,expirydate,userId);
    }
}
