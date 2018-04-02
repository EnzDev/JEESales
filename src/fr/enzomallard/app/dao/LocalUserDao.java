package fr.enzomallard.app.dao;

import fr.enzomallard.app.beans.User;

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
    public User get(String id) {
        return null;
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
