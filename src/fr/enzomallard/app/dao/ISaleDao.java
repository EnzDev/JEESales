package fr.enzomallard.app.dao;

import fr.enzomallard.app.beans.Sale;
import fr.enzomallard.app.beans.User;
import fr.enzomallard.app.DaoFactory;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ISaleDao implements SaleDao {

    private final DaoFactory dao;

    public ISaleDao(DaoFactory dao) {
        this.dao = dao;
    }

    @Override
    public boolean create(Sale sale) throws SQLException {
        PreparedStatement statement = dao.getConnection().prepareStatement("INSERT INTO ANNONCES VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
        statement.setInt(1, sale.getId());
        statement.setString(2, sale.getTitre());
        statement.setString(3, sale.getDescription());
        statement.setString(4, sale.getAcheteur().getId());
        statement.setDate(5, (Date) sale.getCreation());
        statement.setDate(6, (Date) sale.getModification());
        statement.setDouble(7, sale.getPrix());
        statement.setInt(8, sale.getStatut());
        statement.setString(9, sale.getAcheteur().getId());
        statement.setDate(10, (Date) sale.getAchat());
        statement.setLong(11, sale.getNbVues());

        return statement.execute();
    }

    @Override
    public boolean update(Sale sale) throws SQLException {
        PreparedStatement statement = dao.getConnection()
                .prepareStatement("UPDATE ANNONCES SET " +
                        "TITLE = ?," +
                        "CONTENT = ?," +
                        "USER_ID = ?," +
                        "CREATION_DATE = ?," +
                        "UPDATE_DATE = ?," +
                        "PRIX = ?," +
                        "STATUS = ?," +
                        "BUYER = ?," +
                        "BUY_DATE = ?," +
                        "VUES = ? WHERE ID = ?;");

        statement.setString(1, sale.getTitre());
        statement.setString(2, sale.getDescription());
        statement.setString(3, sale.getAcheteur().getId());
        statement.setDate(4, (Date) sale.getCreation());
        statement.setDate(5, (Date) sale.getModification());
        statement.setDouble(6, sale.getPrix());
        statement.setInt(7, sale.getStatut());
        statement.setString(8, sale.getAcheteur().getId());
        statement.setDate(9, (Date) sale.getAchat());
        statement.setLong(10, sale.getNbVues());

        // WHERE ID=
        statement.setInt(11, sale.getId());
        return statement.execute();
    }

    @Override
    public Sale get(int id) throws SQLException {
        PreparedStatement statement = dao.getConnection()
                .prepareStatement("SELECT * FROM ANNONCES WHERE ID = ?");
        statement.setInt(1, id);
        ResultSet result = statement.executeQuery();
        
        UserDao userDao = new IUserDao(this.dao);
        
        Sale current = null;
        while(result.next()){
            current = new Sale();
            current.setId(result.getInt(1));
            current.setTitre(result.getString(2));
            current.setDescription(result.getString(3));
            current.setAcheteur(userDao.get(result.getString(4)));
            current.setCreation(result.getDate(5));
            current.setModification(result.getDate(6));
            current.setPrix(result.getDouble(7));
            current.setStatut(result.getInt(8));
            current.setAcheteur(userDao.get(result.getString(9)));
            current.setAchat(result.getDate(10));
            current.setNbVues(result.getLong(11));
        }

        return current; // Will return null if nothing has been found
    }

    @Override
    public List<Sale> get(User user) throws SQLException {
        ArrayList<Sale> sales = new ArrayList<>();

        PreparedStatement statement = dao.getConnection()
                .prepareStatement("SELECT id FROM ANNONCES WHERE USER_ID=?");
        statement.setString(1, user.getId());
        ResultSet result = statement.executeQuery();
        
        while(result.next())
            sales.add(this.get(result.getInt(1)));

        return sales;
    }
}
