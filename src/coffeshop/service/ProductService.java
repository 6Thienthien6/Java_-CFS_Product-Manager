package coffeshop.service;


import coffeshop.utils.CSVUtils;
import coffeshop.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductService implements IProductService {

    private final List<Product> list = new ArrayList<>();
    public final static String PATH = "data/product.csv";


//    public ProductService() {
//
//    }


    public void update(Product newProduct) {
        List<Product> list = getItem();
        for (Product product : list) {
            if (product.getId() == newProduct.getId()) {
                product.setPrice(newProduct.getPrice());
                product.setQuantity((newProduct.getQuantity()));
            }
        }
        CSVUtils.write(PATH, list);


    }





    public void addProduct(Product newProduct) {
        List<Product> list = getItem();
        list.add(newProduct);
        CSVUtils.write(PATH, list);
    }

    @Override
    public void remove(long id) {
        List<Product> products = getItem();
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                products.remove(i);
                break;
            }
        }
        CSVUtils.write(PATH, products);
    }


    public boolean existByName(String name) {
        List<Product> list = new ArrayList<>();
        for (Product product : list) {
            if (list.equals(product.getName().toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public boolean exists(long id) {
        return getProductByID(id) != null;
    }

    public Product getProductByID(long id) {
        List<Product> list = getItem();
        for (Product product : list) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }


    public List<Product> getItem() {
        List<Product> list = new ArrayList<>();
        List<String> records = CSVUtils.read(PATH);
        for (String record : records) {
            list.add(Product.parse(record));
        }
        return list;

    }

    public int findIndexById(long id) {
        return 0;
    }
}



