package by.yurovski.dao;

import by.yurovski.entity.User;
import by.yurovski.exception.DaoException;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDaoTest {
    @Ignore
    @Test
    public void checkLoginPassword() throws DaoException {
        String login="anton8739";
        String password="1111";
        User user=UserDao.getInstance().checkLoginPassword(login,password);
        String result=user.getSurname();
        String expected="Yurovski";
        assertEquals(result,expected);

    }
    @Ignore
    @Test
    public void getUserById() throws DaoException{
        int id=3;
        User user=UserDao.getInstance().getUserById(id);
        String result=user.getLogin();
        String expacted="anton8739";
        assertEquals(result,expacted);

    }
}