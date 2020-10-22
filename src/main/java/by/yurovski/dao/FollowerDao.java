package by.yurovski.dao;


import by.yurovski.dao.pool.ConnectionPool;
import by.yurovski.dao.util.ConnectionUtil;
import by.yurovski.entity.Follower;
import by.yurovski.entity.LikeFoto;
import by.yurovski.entity.User;
import by.yurovski.exception.DaoException;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FollowerDao {
    private static FollowerDao instance = new FollowerDao();
    private FollowerDao (){}
    public static FollowerDao getInstance (){
        return instance;
    }

    private static final String SELECT_FOLLOWINGS_OF_CURRENT_USER = "SELECT * FROM \"follower\" WHERE followerid=? ";
    private static final String SELECT_FOLLOWERS_OF_CURRENT_USER="SELECT * FROM \"follower\" WHERE followingid=? ";
    private static final String CHECK_IF_IS_LIKED_BY_CURRENT_USER="SELECT * FROM \"follower\" WHERE followerid=? AND followingid=?";
    private static final String INSERT_FOLLOWER = "INSERT INTO \"follower\" (followerid, followingid) VALUES " +
            "(?, ?)";
    private static final String DELETE_FOLLOWER = "DELETE FROM \"follower\" WHERE id=?";
    /**
     * Insert follower into database
     * @param follower
     * @return auto incremented id
     * @throws DaoException
     */
    public int save(Follower follower) throws DaoException{
        int followerId = -1;
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(INSERT_FOLLOWER,
                PreparedStatement.RETURN_GENERATED_KEYS)) {
            connection.setAutoCommit(false);
            statement.setInt(1, follower.getFollowerId());
            statement.setInt(2, follower.getFollowingId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()){
                followerId = resultSet.getInt(1);
            }
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            ConnectionUtil.rollbackConnection(connection, "FollowerDao.save(Follower follower)");
            throw new DaoException("FollowerDao.save(Follower follower)", e);

        } finally {
            ConnectionUtil.closeConnection(connection, "FollowerDao.save(Follower follower)");
        }
        return  followerId;
    }
    /**
     * Delete follower from database
     * @param follower
     * @return void
     * @throws DaoException
     */
    public void delete(Follower follower) throws DaoException{
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(DELETE_FOLLOWER)) {
            connection.setAutoCommit(false);
            statement.setInt(1, follower.getId());
            statement.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            ConnectionUtil.rollbackConnection(connection, "FollowerDao.delete(Follower follower)");
            throw new DaoException("FollowerDao.delete(Follower follower)", e);

        } finally {
            ConnectionUtil.closeConnection(connection, "FollowerDao.delete(Follower follower)");
        }

    }
    /**
     * Select all followings of current user from database.
     * @param userId
     * @return List<Integer> if the match was found and null otherwise
     * @throws DaoException
     */
    public List<Integer> getFollowingsIDs(int userId) throws DaoException{
        List<Integer> list= new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_FOLLOWINGS_OF_CURRENT_USER)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                list.add(resultSet.getInt("followingid"));
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new DaoException("Exception in UserDao.getFollowingsIDs()", e);
        }
        return list;
    }
    /**
     * Select all followers of current user from database.
     * @param userId
     * @return List<Integer> if the match was found and null otherwise
     * @throws DaoException
     */
    public List<Integer> getFollowersIDs(int userId) throws DaoException{
        User user = null;
        List<Integer> list= new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_FOLLOWERS_OF_CURRENT_USER)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                list.add(resultSet.getInt("followerid"));
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new DaoException("Exception in UserDao.getFollowersIDs()", e);
        }
        return list;
    }
    /**
     * Check if current user followed on user.
     * @param  loginedUser,user
     * @return true if followed and false otherwise
     * @throws DaoException
     */
    public Follower isFollowed(User loginedUser, User user) throws DaoException{
        Follower follower=null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(CHECK_IF_IS_LIKED_BY_CURRENT_USER)) {
            statement.setInt(1, loginedUser.getId());
            statement.setInt(2,user.getId());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                follower=getFollowerFromResultSet(resultSet);
            }
            resultSet.close();

        } catch (SQLException e) {
            throw new DaoException("Exception in UserDao.isLikedByCurrentUser()", e);
        }
        return follower;
    }
    /**
     * @param resultSet
     * @return Follower selected from resultSet
     * @throws SQLException
     */
    private Follower getFollowerFromResultSet (ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        int followerId = resultSet.getInt("followerid");
        int followingId = resultSet.getInt("followingid");

        return new Follower(id, followerId,followingId);
    }
}
