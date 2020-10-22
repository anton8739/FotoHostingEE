package by.yurovski.service;

import by.yurovski.dao.CommentDao;
import by.yurovski.dao.FotoDao;
import by.yurovski.dao.LikeCommentDao;
import by.yurovski.dao.LikeFotoDao;
import by.yurovski.dao.pool.ConnectionPool;
import by.yurovski.entity.Comment;
import by.yurovski.entity.Foto;
import by.yurovski.entity.LikeComment;
import by.yurovski.exception.DaoException;
import by.yurovski.exception.ServiceException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class CommentService {
    private static CommentService  instance = new CommentService ();
    private CommentService (){}
    public static CommentService  getInstance (){
        return instance;
    }

    /**
     * Insert comment into database
     * @param comment
     * @return auto incremented id
     * @throws ServiceException
     */
    public int save(Comment comment) throws ServiceException{
        try {
            return CommentDao.getInstance().save(comment);
        } catch (DaoException e) {
            throw new ServiceException("Exception in CommentService.save()", e);
        }
    }
    /**
     * Delete comment from database
     * @param comment
     * @return void
     * @throws ServiceException
     */
    public void delete(Comment comment) throws ServiceException{
        try {
            CommentDao.getInstance().delete(comment);
        } catch (DaoException e) {
            throw new ServiceException("Exception in CommentService.delete()", e);
        }

    }
    /**
     * Get Comment from database by Comment id
     * @param commentId
     * @return Foto object if the match was found and null otherwise
     * @throws ServiceException
     */
    public Comment getCommentById(int commentId) throws ServiceException{
        try {
            return CommentDao.getInstance().getCommentById(commentId);
        } catch (DaoException e) {
            throw new ServiceException("Exception in CommentService.delete()", e);
        }
    }
    /**
     * Select all comment of current foto from database.
     * @param fotoId
     * @return List<Comment> if the match was found and null otherwise
     * @throws ServiceException
     */
    public List<Comment> getCommentsByFotoID(int fotoId) throws ServiceException {
        List<Comment> comments=new ArrayList<>();
        try {
            List<Integer> commentIdList=CommentDao.getInstance().getComentsIdByFotoID(fotoId);
            for(int id:commentIdList){
                comments.add(CommentDao.getInstance().getCommentById(id));
            }

        } catch (DaoException e){
            throw new ServiceException("Exception in FotoService.getFotosByUserId()", e);
        }
        return comments;
    }
    /**
     * Create commentMap<Comment,List<String>> for fotoPage representation
     * @param  fotoId
     * @return LinkedHashMap<Comment,List<String>> if the match was found and null otherwise
     * @throws ServiceException
     */
    public LinkedHashMap<Comment,List<String>> getCommentInformationMap(int fotoId, int userId, int logineduserId) throws ServiceException{
        LinkedHashMap<Comment,List<String>> map= new LinkedHashMap<>();
        List<Comment> commentList=getCommentsByFotoID( fotoId);
        try {
            for(Comment comment:commentList){

                List<String> strings= new ArrayList<String>(3);
                strings.add(String.valueOf(LikeCommentDao.getInstance().getNumberOfLikesByCommentId(comment.getId())));
                if(LikeCommentDao.getInstance().isLikedByCurrentUser(logineduserId,comment.getId())!=null){
                    strings.add(""+1);
                } else {
                    strings.add(""+0);
                }
                strings.add(UserService.getInstance().getUserById(comment.getUserid()).getLogin());
                map.put(comment,strings);
            }
        } catch (DaoException e){
            throw new ServiceException("Exception in FotoService.getFotoInformationMap()", e);
        }
        return map;
    }

}
