package coffeshop.view;

import coffeshop.menu.Menu;
import coffeshop.service.ProductService;
import coffeshop.model.Product;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;

public class ProductView {
    private final DecimalFormat decimalFormat = new DecimalFormat("###,###,###" + " vnđ");
    private static final Scanner SCANNER = new Scanner(System.in);
    //    private static final String PATH = "data/product.csv";
    private final ProductService productService = new ProductService();

    public void addProduct() {
        long id = System.currentTimeMillis() / 1000;
        System.out.print("Nhập tên sản phẩm\n➨ \t");
        String nameProduct = SCANNER.nextLine();
        System.out.print("Nhập số lượng: ");
        int quantity;
        do {
            quantity = Integer.parseInt(SCANNER.nextLine());
            if (!(quantity > 0 && quantity <= 100)) {
                System.out.println("Số lượng không được nhỏ hơn 0 và lớn hơn 100");
                System.out.print("➨ \t ");
            }
        } while (!(quantity > 0 && quantity <= 100));
        System.out.println("Nhập giá sản phẩm");
        System.out.print("➨ \t ");
        double price;
        do {
            price = Double.parseDouble(SCANNER.nextLine());
            if (!(price > 10000 && price < 100000)) {
                System.out.print("Nhập sai giá!!! Lưu ý: \n Giá của sản phẩm phải nằm trong khoảng từ trên 10.000 đến dưới 100.000\n ➨ \t");
                price = Double.parseDouble(SCANNER.nextLine());
            }
        } while (!(price > 10000 && price < 100000));
        Product product = new Product(id, nameProduct, quantity, price);
        productService.addProduct(product);
        System.out.println("Sản phẩm đã được thêm thành công!");
        show(productService.getItem());


        boolean flag = true;
        do {
            System.out.print(" Nhấn 'a' để thêm sản phẩm \n Nhấn 'b' để quay lại \n Nhấn 'e' để thoát \n");
            System.out.print("➨ \t ");
            String choice = SCANNER.nextLine();
            switch (choice) {
                case "a":
                    addProduct();
                    break;
                case "b":
                    Menu.menuProduct();
                    break;
                case "e":
                    Menu.exit();
                    break;
                default:
                    System.out.println("Xin vui lòng nhập lại!");
                    flag = false;
            }
        } while (!flag);
    }

    public void update() {
        show(productService.getItem());
        System.out.print("Nhập ID cần sửa\n➨ \t ");
        try {
            long id = Long.parseLong(SCANNER.nextLine());
            if (productService.exists(id)) {
                Menu.inputUpdate();
                boolean is = true;
                do {
                    try {
                        String choice = SCANNER.nextLine();
                        switch (choice) {
                            case "1":
                                inputPrice(id);
                                break;
                            case "2":
                                inputQuantity(id);
                                break;
                            case "0":
                                Menu.menuProduct();
                                break;
                            default:
                                System.out.print("Chưa hợp lệ!! Mời Nhập Lại\n");
                                is = false;
                        }
                    } catch (Exception e) {
                        update();
                    }
                } while (!is);
                boolean flag = true;
                do {
                    System.out.print("Nhấn 'c' để tiếp tục cập nhật \nNhấn 'b' để quay lại \nNhấn 'e' để thoát... \n=> \t");
                    String chon = SCANNER.nextLine();
                    switch (chon) {
                        case "c":
                            update();
                            break;
                        case "b":
                            Menu.menuProduct();
                            break;
                        case "e":
                            Menu.exit();
                            System.exit(0);
                            break;
                        default:
                            System.out.println("Mời Nhập Lại");
                            flag = false;
                    }
                } while (!flag);
            } else {
                System.out.println("Mời Nhập Lại");
                update();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void show(List<Product> list) {

        System.out.println("╔═════════════════════════════════════════════════DANH SÁCH SẢN PHẨM ════════════════════════════════════════════════════╗");
        System.out.println("‖                                                                                                                        ‖");
        System.out.printf("‖ %-25s %-20s %-20s %-20s", "ID", "Tên Sản Phẩm", "Số lượng", "Giá:                                               ‖");
        System.out.println("");
        for (Product product : list) {
            System.out.printf("‖ %-25d %-20s %-20d %-20s                               ‖\n",
                    product.getId(), product.getName(), product.getQuantity(), decimalFormat.format(product.getPrice()));
        }
        System.out.println("‖                                                                                                                        ‖");
        System.out.println("╚════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝\n");
    }

    public void showAll() {
        List<Product> list = productService.getItem();
        System.out.println("///////////////////////////////////////" + " DANH SÁCH SẢN PHẨM ////////////////////////" + "////////////////////////");
        System.out.println("");
        System.out.printf("%-25s %-20s %-20s %-20s", "ID", "Tên Sản Phẩm", "Số lượng", "Giá: ");
        System.out.println(" ");
        for (Product product : list) {
            System.out.printf("%-25d %-20s %-20d %-20s\n", product.getId(), product.getName(), product.getQuantity(), decimalFormat.format(product.getPrice()));
        }
        System.out.println("");
        System.out.println("/////////////////////////////////////////////////////////////////////////////////////////////////\n");
        Menu.menuProduct();
    }

    public void showAllForUser() {
        List<Product> list = productService.getItem();
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% DANH SÁCH SẢN PHẨM %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        System.out.println("");
        System.out.printf("%-25s %-20s %-20s %-20s", "ID", "Tên Sản Phẩm", "Số lượng", "Giá: ");
        System.out.println(" ");
        for (Product product : list) {
            System.out.printf("%-25d %-20s %-20d %-20s\n", product.getId(), product.getName(), product.getQuantity(), decimalFormat.format(product.getPrice()));
        }
        System.out.println("");
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n");
        Menu menu = new Menu();
        menu.userOrder();
    }

    public void inputPrice(long id) {
        Product product = productService.getProductByID(id);
        System.out.print("Nhập giá sản phẩm: \n➨ \t");
        double price;
        do {
            price = Integer.parseInt(SCANNER.nextLine());
            if (!(price > 10000 && price < 100000)) {
                System.out.print("Nhập sai giá!!! Lưu ý: \n Giá của sản phẩm phải nằm trong khoảng từ trên 10.000 đến dưới 100.000\n ➨ \t");
                price = Double.parseDouble(SCANNER.nextLine());
            }
        } while (!(price > 10000 && price < 100000));
        product.setPrice(price);
        productService.update(product);
        System.out.println("Thay đổi giá sản phẩm thành công!! ");
        show(productService.getItem());

    }

    public void inputQuantity(long id) {
        Product product = productService.getProductByID(id);
        System.out.print("Nhập số lượng: \n➨ \t");
        int quantity;
        do {
            quantity = Integer.parseInt(SCANNER.nextLine());
            if (!(quantity > 0 && quantity <= 100)) {
                System.out.println("Số lượng không được nhỏ hơn 0 và lớn hơn 100");
                System.out.print("➨ \t ");
            }
        } while (!(quantity > 0 && quantity <= 100));
        product.setQuantity(quantity);
        productService.update(product);
        System.out.println("Cập nhật thành công!");
        show(productService.getItem());
    }


    public void remove() {
        List<Product> productList = productService.getItem();
        show(productList);
        System.out.print("Nhập ID sản phẩm \n➨ \t");
        int id = Integer.parseInt(SCANNER.nextLine());
        Product product = productService.getProductByID(id);
        if (product != null) {
            boolean check = true;
            Menu.removeConfirm();

            try {
                do {
                    String chon = SCANNER.nextLine();
                    switch (chon) {
                        case "y":
                            productService.remove(product.getId());
                            System.out.println("Xóa thành công sản phẩm");
                            show(productService.getItem());

                            System.out.println("◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►");
                            System.out.println("◄►        Nhấn 'c' để quay lại      ◄►");
                            System.out.println("◄►        Nhấn 'e' để thoát         ◄►");
                            System.out.println("◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►");
                            System.out.println("➨ \t");
                            chon = SCANNER.nextLine();

                        case "c":
                            Menu.menuProduct();
                            break;
                        case "e":
                            Menu.exit();
                            break;
                        default:
                            System.out.println("\t Nhập sai, xin mời nhập lại!!! ");
                            check = false;
                    }


                } while (!check);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}