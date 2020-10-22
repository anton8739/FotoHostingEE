package by.yurovski.dao;


import by.yurovski.dao.pool.ConnectionPool;
import by.yurovski.dao.util.ConnectionUtil;
import by.yurovski.entity.Foto;
import by.yurovski.entity.LikeFoto;
import by.yurovski.entity.User;
import by.yurovski.enums.UserStatusEnum;
import by.yurovski.exception.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LikeFotoDao {
    private static LikeFotoDao instance = new LikeFotoDao();
    private LikeFotoDao (){}
    public static LikeFotoDao getInstance (){
        return instance;
    }

    private static final String SELECT_LIKES_OF_CURRENT_FOTO = "SELECT * FROM \"likefoto\" WHERE fotowhitchlikedid=? ";
    private static final String CHECK_IF_IS_LIKED_BY_CURRENT_USER="SELECT * FROM \"likefoto\" WHERE userwholikedid=? AND fotowhitchlikedid=?";
    private static final String INSERT_LIKE_FOTO = "INSERT INTO \"likefoto\" (userwholikedid, fotowhitchlikedid) VALUES " +
            "(?, ?)";
    private static final String DELETE_LIKE_FOTO = "DELETE FROM \"likefoto\" WHERE id=?";


    /**
     * Insert like into database
     * @param likeFoto
     * @return auto incremented id
     * @throws DaoException
     */
    public int save(LikeFoto likeFoto) throws DaoException{
        int likefotoid = -1;
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(INSERT_LIKE_FOTO,
                PreparedStatement.RETURN_GENERATED_KEYS)) {
            connection.setAutoCommit(false);
            statement.setInt(1, likeFoto.getUserWhoLikedId());
            statement.setInt(2, likeFoto.getFotoWhichLikedId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()){
                likefotoid = resultSet.getInt(1);
            }
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            ConnectionUtil.rollbackConnection(connection, "LikeFotoDao.save(LikeFotoDao likefoto)");
            throw new DaoException("LikeFotoDao.save(LikeFotoDao likefoto)", e);

        } finally {
            ConnectionUtil.closeConnection(connection, "LikeFotoDao.save(LikeFotoDao likefoto)");
        }
        return  likefotoid;
    }
    /**
     * Delete likefoto from database
     * @param likeFoto
     * @return void
     * @throws DaoException
     */
    public void delete(LikeFoto likeFoto) throws DaoException{
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(DELETE_LIKE_FOTO)) {
            connection.setAutoCommit(false);
            statement.setInt(1, likeFoto.getId());
            statement.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            ConnectionUtil.rollbackConnection(connection, "LikeFotoDao.delete(LikeFoto likeFoto)");
            throw new DaoException("LikeFotoDao.delete(LikeFoto likeFoto)", e);

        } finally {
            ConnectionUtil.closeConnection(connection, "LikeFotoDao.delete(LikeFoto likeFoto)");
        }

    }
    /**
     * Get number of likes for current foto by foto id.
     * @param fotoId
     * * @return int  number of likes
     * @throws DaoException
     */
    public int getNumberOfLikesByFotoId(int fotoId) throws DaoException{
        int counter=0;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_LIKES_OF_CURRENT_FOTO)) {
            statement.setInt(1, fotoId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                counter++;
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new DaoException("Exception in UserDao.getNumberOfLikesByFotoId()", e);
        }
        return counter;
    }
    /**
     * Checks whether foto is liked by current User
     * @param userId
     * * @return boolean true if is liked and false otherwise
     * @throws DaoException
     */
    public LikeFoto isLikedByCurrentUser(int userId, int fotoid) throws DaoException{
        LikeFoto likeFoto=null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(CHECK_IF_IS_LIKED_BY_CURRENT_USER)) {
            statement.setInt(1, userId);
            statement.setInt(2,fotoid);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                likeFoto=getLikeFromResultSet(resultSet);
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new DaoException("Exception in UserDao.isLikedByCurrentUser()", e);
        }
        return likeFoto;
    }
    /**
     * @param resultSet
     * @return Likefoto selected from resultSet
     * @throws SQLException
     */
    private LikeFoto getLikeFromResultSet (ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        int userid = resultSet.getInt("userwholikedid");
        int fotoid = resultSet.getInt("fotowhitchlikedid");

        return new LikeFoto(id, userid,fotoid);
    }
}
