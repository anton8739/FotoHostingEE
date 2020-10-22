package by.yurovski.dao;

import by.yurovski.exception.DaoException;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class FollowerDaoTest {
    @Ignore
    @Test
    public void getFollowingsIDs() throws DaoException {
        int userId=1;
        List<Integer> list = FollowerDao.getInstance().getFollowingsIDs(userId);
        List<Integer> listTest=new ArrayList<>();
        listTest.add(3);
        listTest.add(4);
        assertEquals(listTest,list);
    }
    @Ignore
    @Test
    public void getFollowersIDs() throws DaoException {
        int userId=3;
        List<Integer> list = FollowerDao.getInstance().getFollowersIDs(userId);
        List<Integer> listTest=new ArrayList<>();
        listTest.add(3);
        listTest.add(4);
        assertEquals(listTest,list);
    }
}