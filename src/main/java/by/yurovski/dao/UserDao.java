package by.yurovski.dao;


import by.yurovski.dao.pool.ConnectionPool;
import by.yurovski.dao.util.ConnectionUtil;
import by.yurovski.entity.Foto;
import by.yurovski.entity.User;
import by.yurovski.entity.UserVerificationToken;
import by.yurovski.enums.UserStatusEnum;
import by.yurovski.exception.DaoException;
import by.yurovski.service.UserVerificationTokenService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private static UserDao instance = new UserDao();
    private UserDao (){}
    public static UserDao getInstance (){
        return instance;
    }

    private static final String CHECK_LOGIN_PASSWORD = "SELECT * FROM \"user\" WHERE login=? AND password=?";
    private static final String FIND_USER_BY_ID="SELECT * FROM \"user\" WHERE id=?";
    private static final String FIND_USER_BY_LOGIN="SELECT * FROM \"user\" WHERE login=?";
    private static final String FIND_USER_BY_EMAIL="SELECT * FROM \"user\" WHERE email=?";
    public static final String SELECT_ALL="SELECT * FROM \"user\"";
    private static final String INSERT_USER = "INSERT INTO \"user\" (email,login,password,status,timeofregistration) VALUES " +
            "(?, ?, ?,?,?)";

    private static final String ENABLE_USER = "UPDATE \"user\" SET enabled=? WHERE id=?";
    private static final String EDIT_USER = "UPDATE \"user\" SET name=?,surname=?,email=?,login=? WHERE id=?";
    private static final String DELETE_USER = "DELETE FROM \"user\" WHERE id=?";

    /**
     * Insert user into database
     * @param user
     * @return auto incremented id
     * @throws DaoException
     */
    public int save(User user) throws DaoException{
        int userId = -1;
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(INSERT_USER,
                PreparedStatement.RETURN_GENERATED_KEYS)) {
            connection.setAutoCommit(false);
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getStatus());
            statement.setTimestamp(5, user.getTimeOfRegistration());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()){
                userId = resultSet.getInt("id");
            }

            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            ConnectionUtil.rollbackConnection(connection, "UserDao.save(User user)");
            throw new DaoException("UserDao.save(User user)", e);

        } finally {
            ConnectionUtil.closeConnection(connection, "FotoDao.save(Foto foto)");
        }
        return userId;
    }
    /**
     * Delete user from database
     * @param user
     * @return void
     * @throws DaoException
     */
    public void delete(User user) throws DaoException{
        Connection connection = ConnectionPool.getInstance().getConnection();
            try (PreparedStatement statement = connection.prepareStatement(DELETE_USER)) {
                connection.setAutoCommit(false);
                statement.setInt(1, user.getId());
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
     * Update user in database
     * @param user
     * @return void
     * @throws DaoException
     */
    public void update(User user) throws DaoException{
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(EDIT_USER)) {
            connection.setAutoCommit(false);
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getLogin());
            statement.setInt(5, user.getId());
            statement.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            ConnectionUtil.rollbackConnection(connection, "UserDao.update(User user)");
            throw new DaoException("UserDao.update(User user)", e);

        } finally {
            ConnectionUtil.closeConnection(connection, "UserDao.update(User user)");
        }

    }
    /**
     * Enable user
     * @param user
     * @throws DaoException
     */
    public void enableUser(User user) throws DaoException{
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(ENABLE_USER)) {
            connection.setAutoCommit(false);
            statement.setBoolean(1, true);
            statement.setInt(2, user.getId());
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
     * Select all users from database
     * @return List<User> if the user was found and null otherwise
     * @throws DaoException
     */
    public List<User> selectAll () throws DaoException{
        List<User> userList=new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                userList.add(getUserFromResultSet(resultSet));
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new DaoException("Exception in UserDao.checkLoginPassword()", e);
        }
        return userList;
    }
    /**
     * Checks whether the database contains a pair of login and password
     * @param login
     * @param password
     * @return User object if the match was found and null otherwise
     * @throws DaoException
     */
    public User checkLoginPassword (String login, String password) throws DaoException{
        User user = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(CHECK_LOGIN_PASSWORD)) {
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = getUserFromResultSet(resultSet);
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new DaoException("Exception in UserDao.checkLoginPassword()", e);
        }
        return user;
    }

    /**
     * Checks whether the database contains user whit current id
     * @param userId
     * @return User object if the match was found and null otherwise
     * @throws DaoException
     */

    public User getUserById(int userId) throws DaoException{
        User user =null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_ID)){
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = getUserFromResultSet(resultSet);
            }
            resultSet.close();
        } catch (SQLException e){
            throw  new DaoException("Exception in UserDao.getUserById()", e);
        }
        return user;
    }
    /**
     * Checks whether the database contains user whit current login
     * @param userLogin
     * @return User object if the match was found and null otherwise
     * @throws DaoException
     */
    public User getUserByLogin(String userLogin) throws DaoException{
        User user =null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_LOGIN)){
            statement.setString(1, userLogin);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = getUserFromResultSet(resultSet);
            }
            resultSet.close();
        } catch (SQLException e){
            throw  new DaoException("Exception in UserDao.getUserById()", e);
        }
        return user;
    }
    /**
     * Checks whether the database contains user whit current email
     * @param userEmail
     * @return User object if the match was found and null otherwise
     * @throws DaoException
     */
    public User getUserByEmail(String userEmail) throws DaoException{
        User user =null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_EMAIL)){
            statement.setString(1, userEmail);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = getUserFromResultSet(resultSet);
            }
            resultSet.close();
        } catch (SQLException e){
            throw  new DaoException("Exception in UserDao.getUserById()", e);
        }
        return user;
    }
    /**
     * @param resultSet
     * @return User selected from resultSet
     * @throws SQLException
     */
    private User getUserFromResultSet (ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String surname = resultSet.getString("surname");
        String mail = resultSet.getString("email");
        String login = resultSet.getString("login");
        String password = resultSet.getString("password");
        String mobphone = resultSet.getString("mobphone");
        Timestamp timeOfRegistration=resultSet.getTimestamp("timeofregistration");
        boolean enabled =resultSet.getBoolean("enabled");
        UserStatusEnum status = UserStatusEnum.valueOf(resultSet.getString("status").toUpperCase());

        return new User (id, name, surname,mail, login, password, mobphone,timeOfRegistration,enabled, status);
    }

}
