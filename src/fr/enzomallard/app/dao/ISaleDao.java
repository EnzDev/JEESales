package fr.enzomallard.app.dao;


import fr.enzomallard.app.beans.Sale;
import fr.enzomallard.app.beans.User;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.List;

public interface ISaleDao {

    /**
     * Create a new sale
     *
     * @param sale The bean to inject into the db
     * @return true if it succeed to save the data
     */
    boolean create(Sale sale);


    /**
     * Change data of a Sale (must exist)
     *
     * @param sale the Sale with new data to change
     * @return true if it succeed to update the data
     */
    boolean update(Sale sale);


    /**
     * Get the sale of a specific id
     *
     * @param id The Sale ID
     * @return The Sale bean.
     */
    Sale get(int id);
    // TODO : Change incremental IDs with UIDs !

    /**
     * Get all sales seeable by the specific user
     *
     * @param user The user bean
     * @return A list of all Sales
     */
    List<Sale> get(@Nullable User user, boolean all);

    /**
     * Get the sales of a user
     *
     * @param user The user bean
     * @param all  If this should return all sales of just public ones
     * @return A list of all Sales
     */
    List<Sale> getFrom(User user, boolean all);

    /**
     * Get the size of the DB
     *
     * @return the size
     */
    int size();
}
