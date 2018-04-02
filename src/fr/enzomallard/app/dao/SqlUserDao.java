package fr.enzomallard.app.dao;

import fr.enzomallard.app.DaoFactory;
import fr.enzomallard.app.beans.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqlUserDao implements IUserDao {
    private final DaoFactory dao;

    public SqlUserDao(DaoFactory dao) {
        this.dao = dao;
    }

    @Override
    public boolean create(User user) {
        try {
            PreparedStatement statement = dao.getConnection()
                    .prepareStatement("INSERT INTO USERS VALUES (?, ?, ?, ?, ?);");
            statement.setString(1, user.getId());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getNom());
            statement.setString(4, user.getTelephone());
            statement.setBoolean(5, user.isAdministrateur());

            return statement.execute();
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(User user) {
        try {
            PreparedStatement statement = dao.getConnection()
                    .prepareStatement("DELETE FROM USERS WHERE ID=?;");
            statement.setString(1, user.getId());

            return statement.execute();
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public User get(String id) {
        try {
            PreparedStatement statement = dao.getConnection()
                    .prepareStatement("SELECT * FROM USERS WHERE ID=?;");
            statement.setString(1, id);
            ResultSet result = statement.executeQuery();
            User user = null;
            if (result.next()) {
                user = new User();
                user.setId(result.getString(1));
                user.setPassword(result.getString(2));
                user.setNom(result.getString(3));
                user.setTelephone(result.getString(4));
                user.setAdministrateur(result.getBoolean(5));
            }
            return user;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean check(User user) {
        User dbUser = get(user.getId());
        return dbUser.getPassword().equals(user.getPassword());
    }

    public int size() {
        try {
            ResultSet result = dao.getConnection()
                    .prepareStatement("SELECT count(*) FROM USERS")
                    .executeQuery();
            if (result.next())
                return result.getInt(1);
        } catch (SQLException e) {
            return -1;
        }
        return 0;
    }
}
