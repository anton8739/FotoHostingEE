package by.yurovski.service;

import by.yurovski.dao.CommentDao;
import by.yurovski.dao.FotoDao;
import by.yurovski.dao.LikeFotoDao;
import by.yurovski.dao.UserDao;
import by.yurovski.dao.pool.ConnectionPool;
import by.yurovski.dao.util.ConnectionUtil;
import by.yurovski.entity.Foto;
import by.yurovski.entity.User;
import by.yurovski.exception.DaoException;
import by.yurovski.exception.ServiceException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class FotoService {
    private static FotoService instance = new FotoService();
    private FotoService(){}
    public static FotoService getInstance (){
        return instance;
    }
    /**
     * Insert foto into database
     * @param foto
     * @return auto incremented id
     * @throws ServiceException
     */
    public int save(Foto foto) throws ServiceException{
        try {
        return  FotoDao.getInstance().save(foto);
        } catch (DaoException e){
            throw new ServiceException("Exception in FotoService.save()", e);
        }
    }
    /**
     * Delete foto from database
     * @param foto
     * @return void
     * @throws ServiceException
     */
    public void delete(Foto foto) throws ServiceException{
        try {
            FotoDao.getInstance().delete(foto);
        } catch (DaoException e) {
            throw new ServiceException("Exception in FotoService.delete()", e);
        }

    }
    /**
     * Set foto title and description into database
     * @param foto
     * @throws ServiceException
     */
    public void setTitleAndDescription(Foto foto) throws ServiceException{
        try {
            FotoDao.getInstance().setTitleAndDescription(foto);
        } catch (DaoException e) {
            throw new ServiceException("Exception in FotoService.setTitleAndDescription()", e);
        }
    }
    /**
     * Select all foto of current user from database.
     * @param userId
     * @return List<Foto> if the match was found and null otherwise
     * @throws ServiceException
     */
    public List<Foto> getFotosByUserID(int userId) throws ServiceException {
        List<Foto> fotos=new ArrayList<>();
        try {
            List<Integer> fotoIdList=FotoDao.getInstance().getFotosIdByUserID(userId);
            for(int id:fotoIdList){
                fotos.add(FotoDao.getInstance().getFotoById(id));
            }
            return fotos;
        } catch (DaoException e){
            throw new ServiceException("Exception in FotoService.getFotosByUserId()", e);
        }
    }
    /**
     * Select all fotos of current user from database.
     * @param userId
     * @return List<Integer> if the match was found and null otherwise
     * @throws DaoException
     */
    public List<Integer> getFotosIdByUserID(int userId) throws ServiceException{
        try {
            return  FotoDao.getInstance().getFotosIdByUserID(userId);
        } catch (DaoException e){
            throw new ServiceException("Exception in FotoService.save()", e);
        }
    }
    /**
     * Checks whether the database contains user whit current id
     * @param fotoId
     * @return Foto object if the match was found and null otherwise
     * @throws ServiceException
     */
    public Foto getFotoById(int fotoId) throws ServiceException{
        try {
            return  FotoDao.getInstance().getFotoById(fotoId);
        } catch (DaoException e){
            throw new ServiceException("Exception in FotoService.save()", e);
        }
    }
    /**
     * Checks whether the database contains user whit current name
     * @param fotoName
     * @return Foto object if the match was found and null otherwise
     * @throws ServiceException
     */
    public Foto getFotoByName(String fotoName) throws ServiceException{
        try {
            return  FotoDao.getInstance().getFotoByName(fotoName);
        } catch (DaoException e){
            throw new ServiceException("Exception in FotoService.save()", e);
        }
    }
    /**
     * Get number of foto of current User
     * @param user
     * @return int number of foto.
     * @throws ServiceException
     */
    public int getNumberOfFotoOfCurrentUser(User user) throws ServiceException{
        try {
            return  FotoDao.getInstance().getNumberOfFotoOfCurrentUser(user);
        } catch (DaoException e){
            throw new ServiceException("Exception in FotoService.getNumberOfFotoOfCurrentUser()", e);
        }
    }
    /**
     * Create fotoMap<Foto,List<Integer>>  for userAccountPage representation
     * @param userId
     * @return LinkedHashMap<Foto,List<Integer>> if the match was found and null otherwise
     * @throws ServiceException
     */
    public LinkedHashMap<Foto,List<Integer>> getFotoInformationMap(int userId, int loginedUser) throws ServiceException{
        LinkedHashMap<Foto,List<Integer>> map= new LinkedHashMap<>();
        List<Foto> fotoList=getFotosByUserID(userId);
        try {
        for(Foto foto:fotoList){
            List<Integer> integers= new ArrayList<>(3);
            integers.add(LikeFotoDao.getInstance().getNumberOfLikesByFotoId(foto.getId()));
            if(LikeFotoDao.getInstance().isLikedByCurrentUser(loginedUser,foto.getId())!=null){
                integers.add(1);
            } else {
                integers.add(0);
            }
            integers.add(CommentDao.getInstance().getNumberOfCommentsByFotoId(foto.getId()));
            map.put(foto,integers);
        }
        } catch (DaoException e){
            throw new ServiceException("Exception in FotoService.getFotoInformationMap()", e);
        }
        return map;
    }

}
