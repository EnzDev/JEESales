package fr.enzomallard.app.dao;

import fr.enzomallard.app.DaoFactory;
import fr.enzomallard.app.beans.Sale;
import fr.enzomallard.app.beans.Status;
import fr.enzomallard.app.beans.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SqlSaleDao implements ISaleDao {
    private static final Logger logger = LogManager.getLogger(SqlSaleDao.class);
    private final DaoFactory dao;

    public SqlSaleDao(DaoFactory dao) {
        this.dao = dao;
    }

    @Override
    public boolean create(Sale sale) {
        try {
            PreparedStatement statement = dao.getConnection().prepareStatement("INSERT INTO ANNONCES VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
            statement.setInt(1, sale.getId());
            statement.setString(2, sale.getTitre());
            statement.setString(3, sale.getDescription());
            statement.setString(4, sale.getAcheteur().getId());
            statement.setDate(5, (Date) sale.getCreation());
            statement.setDate(6, (Date) sale.getModification());
            statement.setDouble(7, sale.getPrix());
            statement.setInt(8, sale.getStatus().ordinal());
            statement.setString(9, sale.getAcheteur().getId());
            statement.setDate(10, (Date) sale.getAchat());
            statement.setLong(11, sale.getNbVues());

            return statement.execute();
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean update(Sale sale) {
        try {
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
            statement.setInt(7, sale.getStatus().ordinal());
            statement.setString(8, sale.getAcheteur().getId());
            statement.setDate(9, (Date) sale.getAchat());
            statement.setLong(10, sale.getNbVues());

            // WHERE ID=
            statement.setInt(11, sale.getId());
            return statement.execute();
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Sale get(int id) {
        Sale current = null;
        try {
            PreparedStatement statement = dao.getConnection()
                    .prepareStatement("SELECT * FROM ANNONCES WHERE ID = ?");
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();

            IUserDao IUserDao = new SqlUserDao(this.dao);
            while (result.next()) {
                current = new Sale();
                current.setId(result.getInt(1));
                current.setTitre(result.getString(2));
                current.setDescription(result.getString(3));
                current.setAcheteur(IUserDao.get(result.getString(4)));
                current.setCreation(result.getDate(5));
                current.setModification(result.getDate(6));
                current.setPrix(result.getDouble(7));
                current.setStatut(Status.values()[result.getInt(8)]);
                current.setAcheteur(IUserDao.get(result.getString(9)));
                current.setAchat(result.getDate(10));
                current.setNbVues(result.getLong(11));
            }
        } catch (SQLException ignored) {
            // Current is null, that what we want if the query fail
            // TODO: 08/03/18 Add a log
        }
        return current; // Will return null if nothing has been found
    }

    @Override
    public List<Sale> get(User user) {
        ArrayList<Sale> sales = new ArrayList<>();
        if (user == null)
            user = new User();
        try {
            PreparedStatement statement = dao.getConnection()
                    .prepareStatement("SELECT id FROM ANNONCES"/*" WHERE USER_ID=? OR STATUS=1"*/);

            // statement.setString(1, user.getId());
            ResultSet result = statement.executeQuery();

            while (result.next())
                sales.add(this.get(result.getInt(1)));

        } catch (SQLException e) { // sales will be just empty
            logger.error("Could not connect to the database");
        }
        return sales;
    }

    @Override
    public List<Sale> getFrom(User user, boolean all) {
        ArrayList<Sale> sales = new ArrayList<>();

        try {
            PreparedStatement statement;
            statement = dao.getConnection()
                    .prepareStatement("SELECT id FROM ANNONCES WHERE USER_ID=?");

            statement.setString(1, user.getId());
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Sale sale = this.get(result.getInt(1));
                if (all || sale.getStatus() == Status.PUBLIE)
                    sales.add(sale);
            }

        } catch (SQLException e) { // sales will be just empty
            logger.error("Could not connect to the database");
        }
        return sales;

    }

    public int size() {
        try {
            ResultSet result = dao.getConnection()
                    .prepareStatement("SELECT count(*) FROM ANNONCES")
                    .executeQuery();
            if (result.next()) {
                return result.getInt(1);
            }
        } catch (SQLException e) {
            return -1;
        }
        return 0;
    }
}
