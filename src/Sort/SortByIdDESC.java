package Sort;

import CoffeShop.model.Product;

import java.util.Comparator;

public class SortByIdDESC implements Comparator<Product> {
    @Override
    public int compare(Product o1, Product o2) {
        return (int) (o2.getId() - o1.getId());
    }
}
