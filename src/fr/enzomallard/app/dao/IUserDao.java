package fr.enzomallard.app.dao;

import fr.enzomallard.app.beans.User;

public interface IUserDao {
    boolean create(User user);

    boolean delete(User user);

    User get(String id);

    boolean check(User user);

    int size();
}

