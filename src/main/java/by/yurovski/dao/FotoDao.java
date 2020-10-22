package by.yurovski.dao;


import by.yurovski.dao.pool.ConnectionPool;
import by.yurovski.dao.util.ConnectionUtil;
import by.yurovski.entity.Foto;
import by.yurovski.entity.User;
import by.yurovski.enums.UserStatusEnum;
import by.yurovski.exception.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FotoDao {
    private static FotoDao  instance = new FotoDao ();
    private FotoDao  (){}
    public static FotoDao  getInstance (){
        return instance;
    }
    private static final String SELECT_FOTOS_OF_CURRENT_USER = "SELECT * FROM \"foto\" WHERE userid=? ";
    private static final String FIND_FOTO_BY_ID= "SELECT * FROM \"foto\" WHERE id=? ";
    private static final String FIND_FOTO_BY_NAME= "SELECT * FROM \"foto\" WHERE name=? ";
    private static final String INSERT_FOTO = "INSERT INTO \"foto\" (name, url, userid, timeofcreation) VALUES " +
            "(?, ?, ?, ?)";
    private static final String GET_NUMBER_OF_FOTO= "SELECT count(*) as total FROM \"foto\" WHERE userid=? ";
    private static final String DELETE_FOTO = "DELETE FROM \"foto\" WHERE id=?";
    private static final String SET_TITLE_DESCRIPTION_OF_FOTO = "UPDATE \"foto\" SET title=?, text=? WHERE id=?";
    /**
     * Insert foto into database
     * @param foto
     * @return auto incremented id
     * @throws DaoException
     */
    public int save(Foto foto) throws DaoException{
        int fotoId = -1;
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(INSERT_FOTO,
                PreparedStatement.RETURN_GENERATED_KEYS)) {
            connection.setAutoCommit(false);
            statement.setString(1, foto.getName());
            statement.setString(2, foto.getURL());
            statement.setInt(3, foto.getUserId());
            statement.setTimestamp(4, foto.getTimeOfCreation());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()){
                fotoId = resultSet.getInt(1);
            }

            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            ConnectionUtil.rollbackConnection(connection, "FotoDao.save(Foto foto)");
            throw new DaoException("FotoDao.save(Foto foto)", e);

        } finally {
            ConnectionUtil.closeConnection(connection, "FotoDao.save(Foto foto)");
        }
        return fotoId;
    }
    /**
     * Delete foto from database
     * @param foto
     * @return void
     * @throws DaoException
     */
    public void delete(Foto foto) throws DaoException{
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(DELETE_FOTO)) {
            connection.setAutoCommit(false);
            statement.setInt(1, foto.getId());
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
     * Set foto title and description into database
     * @param foto
     * @throws DaoException
     */
    public void setTitleAndDescription(Foto foto) throws DaoException{
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SET_TITLE_DESCRIPTION_OF_FOTO)) {
            connection.setAutoCommit(false);
            statement.setString(1, foto.getTitle());
            statement.setString(2, foto.getText());
            statement.setInt(3, foto.getId());
            statement.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            ConnectionUtil.rollbackConnection(connection, "FotoDao.setTitleAndDescription(Foto foto)");
            throw new DaoException("FotoDao.setTitleAndDescription(Foto foto)", e);

        } finally {
            ConnectionUtil.closeConnection(connection, "FotoDao.setTitleAndDescription(Foto foto)");
        }
    }
    /**
     * Select all fotos of current user from database.
     * @param userId
     * @return List<Integer> if the match was found and null otherwise
     * @throws DaoException
     */
    public List<Integer> getFotosIdByUserID(int userId) throws DaoException{
        List<Integer> list= new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_FOTOS_OF_CURRENT_USER)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                list.add(resultSet.getInt("id"));
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new DaoException("Exception in UserDao.getFotosByUserID()", e);
        }
        return list;
    }
    /**
     * Checks whether the database contains user whit current id
     * @param fotoId
     * @return Foto object if the match was found and null otherwise
     * @throws DaoException
     */
    public Foto getFotoById(int fotoId) throws DaoException{
        Foto foto =null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_FOTO_BY_ID)){
            statement.setInt(1, fotoId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                foto = getFotoFromResultSet(resultSet);
            }
            resultSet.close();
        } catch (SQLException e){
            throw  new DaoException("Exception in FotoDao.getFotoById()", e);
        }
        return foto;
    }
    /**
     * Checks whether the database contains user whit current name
     * @param fotoName
     * @return Foto object if the match was found and null otherwise
     * @throws DaoException
     */
    public Foto getFotoByName(String fotoName) throws DaoException{
        Foto foto =null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_FOTO_BY_NAME)){
            statement.setString(1, fotoName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                foto = getFotoFromResultSet(resultSet);
            }
            resultSet.close();
        } catch (SQLException e){
            throw  new DaoException("Exception in FotoDao.getFotoById()", e);
        }
        return foto;
    }
    /**
     * Get number of foto of current User
     * @param user
     * @return int number of foto.
     * @throws DaoException
     */
    public int getNumberOfFotoOfCurrentUser(User user) throws DaoException{
        int result;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_NUMBER_OF_FOTO)){
            statement.setInt(1, user.getId());
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            result=resultSet.getInt("total");
            resultSet.close();
        } catch (SQLException e){
            throw  new DaoException("Exception in FotoDao.getNumberOfFotoOfCurrentUser()", e);
        }
        return result;
    }
    /**
     * @param resultSet
     * @return Foto selected from resultSet
     * @throws SQLException
     */
    private Foto getFotoFromResultSet (ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String url = resultSet.getString("url");
        String text = resultSet.getString("text");
        String title = resultSet.getString("title");
        Timestamp timeOfCreation = resultSet.getTimestamp("timeofcreation");
        int userid = resultSet.getInt("userid");
        return new Foto (id,name,url,text,title,userid,timeOfCreation);
    }

}
