package fr.enzomallard.app.dao;

import fr.enzomallard.app.beans.User;

import java.util.Set;

public interface IUserDao {
    boolean create(User user);

    boolean delete(User user);

    boolean update(User user);

    User get(String id);

    Set<User> getAll();

    boolean check(User user);

    int size();
}

