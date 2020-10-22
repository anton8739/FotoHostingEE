package by.yurovski.dao;


import by.yurovski.dao.pool.ConnectionPool;
import by.yurovski.dao.util.ConnectionUtil;
import by.yurovski.entity.LikeComment;
import by.yurovski.entity.LikeFoto;
import by.yurovski.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LikeCommentDao {
    private static LikeCommentDao instance = new LikeCommentDao();
    private LikeCommentDao (){}
    public static LikeCommentDao getInstance (){
        return instance;
    }
    private static final String SELECT_LIKES_OF_CURRENT_COMMENT = "SELECT * FROM \"likecomment\" WHERE commentwhitchlikedid=? ";
    private static final String CHECK_IF_IS_LIKED_BY_CURRENT_USER="SELECT * FROM \"likecomment\" WHERE userwholikedid=? AND commentwhitchlikedid=?";
    private static final String INSERT_LIKE_COMMENT = "INSERT INTO \"likecomment\" (userwholikedid, commentwhitchlikedid) VALUES " +
            "(?, ?)";
    private static final String DELETE_LIKE_COMMENT = "DELETE FROM \"likecomment\" WHERE id=?";
    /**
     * Insert like into database
     * @param likeComment
     * @return auto incremented id
     * @throws DaoException
     */
    public int save(LikeComment likeComment) throws DaoException{
        int likeCommentId = -1;
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(INSERT_LIKE_COMMENT,
                PreparedStatement.RETURN_GENERATED_KEYS)) {
            connection.setAutoCommit(false);
            statement.setInt(1, likeComment.getUserWhoLikedId());
            statement.setInt(2, likeComment.getCommentWhichLikedId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()){
                likeCommentId = resultSet.getInt(1);
            }
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            ConnectionUtil.rollbackConnection(connection, "LikeCommentDao.save(LikeComment likeComment)");
            throw new DaoException("LikeCommentDao.save(LikeComment likeComment)", e);

        } finally {
            ConnectionUtil.closeConnection(connection, "LikeCommentDao.save(LikeComment likeComment)");
        }
        return  likeCommentId;
    }
    /**
     * Delete likefoto from database
     * @param likeComment
     * @return void
     * @throws DaoException
     */
    public void delete(LikeComment likeComment) throws DaoException{
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(DELETE_LIKE_COMMENT)) {
            connection.setAutoCommit(false);
            statement.setInt(1, likeComment.getId());
            statement.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            ConnectionUtil.rollbackConnection(connection, "LikeCommentDao.delete(LikeComment likeComment)");
            throw new DaoException("LikeCommentDao.delete(LikeComment likeComment)", e);

        } finally {
            ConnectionUtil.closeConnection(connection, "LikeCommentDao.delete(LikeComment likeComment)");
        }

    }
    /**
     * Get number of likes for current comment by comment id.
     * @param commentId
     * * @return int  number of likes
     * @throws DaoException
     */
    public int getNumberOfLikesByCommentId(int commentId) throws DaoException{
        int counter=0;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_LIKES_OF_CURRENT_COMMENT)) {
            statement.setInt(1, commentId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                counter++;
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new DaoException("Exception in UserDao.getNumberOfLikesByCommentId()", e);
        }
        return counter;
    }
    /**
     * Checks whether comment is liked by current User
     * @param userId,commentId
     * * @return boolean true if is liked and false otherwise
     * @throws DaoException
     */
    public LikeComment isLikedByCurrentUser(int userId, int commentId) throws DaoException{
        LikeComment likeComment=null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(CHECK_IF_IS_LIKED_BY_CURRENT_USER)) {
            statement.setInt(1, userId);
            statement.setInt(2, commentId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                likeComment=getLikeFromResultSet(resultSet);
            }
            resultSet.close();

        } catch (SQLException e) {
            throw new DaoException("Exception in UserDao.isLikedByCurrentUser()", e);
        }
        return likeComment;
    }
    /**
     * @param resultSet
     * @return LikeComment selected from resultSet
     * @throws SQLException
     */
    private LikeComment getLikeFromResultSet (ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        int userid = resultSet.getInt("userwholikedid");
        int commentid = resultSet.getInt("commentwhitchlikedid");

        return new LikeComment(id, userid,commentid);
    }
}
