package fr.enzomallard.app.dao;

import fr.enzomallard.app.beans.Sale;
import fr.enzomallard.app.beans.User;

import java.sql.SQLException;
import java.util.List;

public interface SaleDao {
    boolean create(Sale sale) throws SQLException;

    boolean update(Sale sale) throws SQLException;

    Sale get(int id) throws SQLException;
    // TODO : Change incremental IDs with UIDs !

    List<Sale> get(User user) throws SQLException;
}
