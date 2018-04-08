package fr.enzomallard.app.dao;

import fr.enzomallard.app.beans.Sale;
import fr.enzomallard.app.beans.Status;
import fr.enzomallard.app.beans.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LocalSaleDao implements ISaleDao {
    private static ArrayList<Sale> localData;

    public LocalSaleDao() {
        localData = new ArrayList<>();
        Sale sale1 = new Sale();
        sale1.setId(1);
        sale1.setTitre("Livre 1");
        sale1.setVendeur(new User());
        sale1.getVendeur().setId("ADMIN");
        localData.add(sale1);

        Sale sale2 = new Sale();
        sale2.setId(2);
        sale2.setTitre("Livre 2");
        sale2.setVendeur(new User());
        sale2.getVendeur().setId("ADMIN");
        localData.add(sale2);

        Sale sale3 = new Sale();
        sale3.setId(2);
        sale3.setTitre("Livre 3");
        sale3.setVendeur(new User());
        sale3.getVendeur().setId("TOTO");
        sale3.setStatut(Status.CANCELED);
        localData.add(sale3);
    }

    @Override
    public boolean create(Sale sale) {
        return false;
    }

    @Override
    public boolean update(Sale sale) {
        return false;
    }

    @Override
    public Sale get(int id) {
        return localData.stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Sale> get(User user) {
        return localData.stream()
                .filter(s -> s.getVendeur().getId().equals(user.getId()) || s.getStatus() == Status.PUBLISHED)
                .collect(Collectors.toList());
    }

    @Override
    public List<Sale> getFrom(User user, boolean all) {
        return new ArrayList<>();
    }

    @Override
    public int size() {
        return 0;
    }
}
