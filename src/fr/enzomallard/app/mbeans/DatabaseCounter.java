package fr.enzomallard.app.mbeans;

import fr.enzomallard.app.DaoFactory;
import fr.enzomallard.app.mbeans.interfaces.DatabaseCounterMBean;

/**
 * Created by Enzo Mallard
 * The 09/04/2018
 */
public class DatabaseCounter implements DatabaseCounterMBean {
    @Override
    public int getRegisteredUser() {
        return DaoFactory.getInstance().getUserDao().size();
    }

    @Override
    public int getSavedSales() {
        return DaoFactory.getInstance().getSaleDao().size();
    }
}
