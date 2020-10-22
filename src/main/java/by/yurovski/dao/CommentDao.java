package by.yurovski.dao;


import by.yurovski.dao.pool.ConnectionPool;
import by.yurovski.dao.util.ConnectionUtil;
import by.yurovski.entity.Comment;
import by.yurovski.entity.Foto;
import by.yurovski.entity.LikeComment;
import by.yurovski.exception.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentDao {
    private static CommentDao  instance = new CommentDao ();
    private CommentDao  (){}
    public static CommentDao  getInstance (){
        return instance;
    }
    private static final String GET_NUMBER_OF_COMMENTS_BY_FOTO = "SELECT * FROM \"comment\" WHERE fotoid=? ";
    private static final String SELECT_COMMENTS_OF_CURRENT_FOTO = "SELECT * FROM \"comment\" WHERE fotoid=? ";
    private static final String FIND_COMMENT_BY_ID= "SELECT * FROM \"comment\" WHERE id=? ";
    private static final String DELETE_COMMENT = "DELETE FROM \"comment\" WHERE id=?";
    private static final String INSERT_COMMENT = "INSERT INTO \"comment\" (text, timeofcreation, userid, fotoid) VALUES " +
            "(?, ?,?,?)";

    /**
     * Insert comment into database
     * @param comment
     * @return auto incremented id
     * @throws DaoException
     */
    public int save(Comment comment) throws DaoException{
        int commentId = -1;
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(INSERT_COMMENT,
                PreparedStatement.RETURN_GENERATED_KEYS)) {
            connection.setAutoCommit(false);
            statement.setString(1,comment.getText());
            statement.setTimestamp(2,comment.getTimeOfCreation());
            statement.setInt(3, comment.getUserid());
            statement.setInt(4,comment.getFotoid());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()){
                commentId = resultSet.getInt(1);
            }
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            ConnectionUtil.rollbackConnection(connection, "LikeCommentDao.save(LikeComment likeComment)");
            throw new DaoException("LikeCommentDao.save(LikeComment likeComment)", e);

        } finally {
            ConnectionUtil.closeConnection(connection, "LikeCommentDao.save(LikeComment likeComment)");
        }
        return  commentId;
    }
    /**
     * Delete comment from database
     * @param comment
     * @return void
     * @throws DaoException
     */
    public void delete(Comment comment) throws DaoException{
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(DELETE_COMMENT)) {
            connection.setAutoCommit(false);
            statement.setInt(1, comment.getId());
            statement.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            ConnectionUtil.rollbackConnection(connection, "UserDao.save(User user)");
            throw new DaoException("UserDao.save(User user)", e);

        } finally {
            ConnectionUtil.closeConnection(connection, "FotoDao.save(Foto foto)");
        }

    }
    /**
     * Get number of comment for current foto by foto id.
     * @param fotoId
     * @return int  number of comments.
     * @throws DaoException
     */
    public int getNumberOfCommentsByFotoId(int fotoId) throws DaoException{
        int counter=0;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_NUMBER_OF_COMMENTS_BY_FOTO)) {
            statement.setInt(1, fotoId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                counter++;
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new DaoException("Exception in UserDao.getNumberOfCommentsByFotoId()", e);
        }
        return counter;
    }
    /**
     * Select id's of comments  of current foto from database.
     * @param fotoId
     * @return List<Integer> if the match was found and null otherwise
     * @throws DaoException
     */
    public List<Integer> getComentsIdByFotoID(int fotoId) throws DaoException{
        List<Integer> list= new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_COMMENTS_OF_CURRENT_FOTO)) {
            statement.setInt(1, fotoId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                list.add(resultSet.getInt("id"));
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new DaoException("Exception in CommentDao.getComentsIdByFotoID()", e);
        }
        return list;
    }
    /**
     * Get Comment from database by Comment id
     * @param commentId
     * @return Foto object if the match was found and null otherwise
     * @throws DaoException
     */
    public Comment getCommentById(int commentId) throws DaoException{
        Comment comment =null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_COMMENT_BY_ID)){
            statement.setInt(1, commentId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                comment = getCommentFromResultSet(resultSet);
            }
            resultSet.close();
        } catch (SQLException e){
            throw  new DaoException("Exception in FotoDao.getFotoById()", e);
        }
        return comment;
    }
    /**
     * @param resultSet
     * @return Commment selected from resultSet
     * @throws SQLException
     */
    private Comment getCommentFromResultSet (ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String text = resultSet.getString("text");
        int userid=resultSet.getInt("userid");
        int fotoid=resultSet.getInt("fotoid");
        Timestamp timeOfCreation = resultSet.getTimestamp("timeofcreation");

        return new Comment (id,text,userid,fotoid,timeOfCreation);
    }
}
