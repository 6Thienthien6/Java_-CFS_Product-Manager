package coffeshop.model;

import java.util.Comparator;

public class Product implements Comparator<Product> {
    private long id;
    private String name;
    private int quantity;
    private double price;
public Product(){

}
    public Product(long id, String name, int quantity, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;

    }

    public static Product parse(String record) {
        String[] fields = record.split(",");
        long id = Long.parseLong(fields[0]);
        String name = fields[1];
        int quantity = Integer.parseInt(fields[2]);
        double price = Double.parseDouble(fields[3]);
        return new Product(id, name, quantity, price);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPrice() {
        return (long) price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s", id, name,quantity, price);

    }


    public int compare(Product o1, Product o2) {
        return (int) (o1.getId() - o2.getId());
    }
}

