package CoffeShop.Service;

import CoffeShop.model.Product;

import java.util.List;

public interface IProductService {
     void update(Product newProduct);
    void addProduct(Product newProduct);
    public boolean exists(long id);
    public void remove(long id);
//    int findIndexById(long id);
//     boolean existByName(String name);
    public Product getProductByID(long id);
////    List<Product> sortByASC();
////    List<Product> sortByDESC();
//    void showAllProducts();
    List<Product> getItem();
}
