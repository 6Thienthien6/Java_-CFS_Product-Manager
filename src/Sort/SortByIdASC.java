package Sort;

import CoffeShop.model.Product;

import java.util.Comparator;

public class SortByIdASC implements Comparator<Product> {
    Product product = new Product();
    @Override
    public int compare(Product o1, Product o2) {
        return (int) (o1.getId() - o2.getId());
    }
}
