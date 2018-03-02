package fr.enzomallard.app.dao;

import fr.enzomallard.app.beans.User;

import java.sql.SQLException;

public interface UserDao {
    boolean create(User user) throws SQLException;

    boolean delete(User user) throws SQLException;

    User get(String id) throws SQLException;

    boolean check(User user);
}

