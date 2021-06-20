package web.dao;


import web.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {

    User getUserById(long id) throws SQLException;

    List<User> getUsersList() throws SQLException;

    void insertUser(User user) throws SQLException;

    void deleteUser(long id) throws SQLException;

    void updateUser(User user) throws SQLException;
}
