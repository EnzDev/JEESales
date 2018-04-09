package fr.enzomallard.app.dao;

import fr.enzomallard.app.beans.User;

import java.util.Set;
import java.util.TreeSet;

public class LocalUserDao implements IUserDao {
    @Override
    public boolean create(User user) {
        return false;
    }

    @Override
    public boolean delete(User user) {
        return false;
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public User get(String id) {
        return null;
    }

    @Override
    public Set<User> getAll() {
        return new TreeSet<>();
    }

    @Override
    public boolean check(User user) {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }
}
