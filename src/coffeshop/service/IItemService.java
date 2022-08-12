package coffeshop.service;

import coffeshop.model.Product;

import java.util.List;

public interface IItemService {
    List<Product> getItem();
    void addItem(Product product);
    void update(Product product);
    void remove(long id);
    boolean exists(int id);
    Product getProductByID(int id);
}
