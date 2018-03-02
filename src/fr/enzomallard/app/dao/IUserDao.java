package fr.enzomallard.app.dao;

import fr.enzomallard.app.DaoFactory;
import fr.enzomallard.app.beans.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IUserDao implements UserDao {
    private final DaoFactory dao;

    public IUserDao(DaoFactory dao) {
        this.dao = dao;
    }

    @Override
    public boolean create(User user) throws SQLException {
        PreparedStatement statement = dao.getConnection()
                .prepareStatement("INSERT INTO USERS VALUES (?, ?, ?, ?, ?);");
        statement.setString(1, user.getId());
        statement.setString(2, user.getPassword());
        statement.setString(3, user.getNom());
        statement.setString(4, user.getTelephone());
        statement.setBoolean(5, user.isAdministrateur());

        return statement.execute();
    }

    @Override
    public boolean delete(User user) throws SQLException {
        PreparedStatement statement = dao.getConnection()
                .prepareStatement("DELETE FROM USERS WHERE ID=?;");
        statement.setString(1, user.getId());

        return statement.execute();
    }

    @Override
    public User get(String id) throws SQLException {
        PreparedStatement statement = dao.getConnection()
                .prepareStatement("SELECT * FROM USERS WHERE ID=?;");
        statement.setString(1, id);
        ResultSet result = statement.executeQuery();
        User user = null;
        if(result.first()){
            user = new User();
            user.setId(result.getString(1));
            user.setPassword(result.getString(2));
            user.setNom(result.getString(3));
            user.setTelephone(result.getString(4));
            user.setAdministrateur(result.getBoolean(5));
        }
        return user;
    }

    @Override
    public boolean check(User user) throws SQLException {
        // NOTE: 02/03/18 Probably the password checking
        // n.    but need to verify
        User dbUser = get(user.getId());
        return dbUser.getPassword().equals(user.getPassword());
    }
}
