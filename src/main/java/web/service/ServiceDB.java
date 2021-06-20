package web.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import web.dao.UserDAO;
import web.dao.UserDaoHibernate;
import web.model.User;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;


@Service
public class ServiceDB {

    private final UserDAO userDAO;

    @Autowired
    public ServiceDB() {
        userDAO = new UserDaoHibernate();
    }

    @Transactional
    public void insertUser(User user) throws SQLException {
        userDAO.insertUser(user);
    }

    @Transactional
    public void deleteUser(long id) throws SQLException {
        userDAO.deleteUser(id);
    }

    @Transactional
    public User getUserById(int id) throws SQLException {
        return userDAO.getUserById(id);
    }

    @Transactional
    public void updateUser(User user) throws SQLException {
        userDAO.updateUser(user);
    }

    @Transactional
    public List<User> listUsers() throws SQLException {
        return userDAO.getUsersList();
    }
}