package crud.service;


import crud.dao.UserDAO;
import crud.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class ServiceDB {
    private UserDAO userDAO;

    @Autowired
    public ServiceDB(UserDAO usersDAO) {
        this.userDAO = usersDAO;
    }

    @Transactional
    public void insertUser(User user) {
        userDAO.add(user);
    }

    @Transactional
    public void deleteUser(int id) {
        userDAO.delete(id);
    }

    @Transactional
    public User getUserById(int id) {
        return userDAO.getById(id);
    }

    @Transactional
    public void updateUser(User user) {
        userDAO.edit(user);
    }

    @Transactional
    public List<User> listUsers() {
        return userDAO.userList();
    }
}