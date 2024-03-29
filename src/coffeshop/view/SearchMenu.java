package coffeshop.view;

import coffeshop.menu.Menu;
import coffeshop.service.ProductService;
import coffeshop.model.Product;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;

public class SearchMenu {
    static Scanner scanner = new Scanner(System.in);
//    static ProductView productView = new ProductView();
    static ProductService productService = new ProductService();
    static DecimalFormat formater = new DecimalFormat("###,###,###" + "vnđ");

    public static void searchMenu() {

        boolean isChoice = true;
        String choose = null;
        do {
            System.out.println("◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►");
            System.out.println("◄►                      TÌM KIẾM SẢN PHẨM                     ◄►");
            System.out.println("◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►");
            System.out.println("◄►                                                            ◄►");
            System.out.println("◄►                    1. Tìm kiếm theo ID                     ◄►");
            System.out.println("◄►                    2. Tìm kiếm theo tên                    ◄►");
            System.out.println("◄►                    3. Tìm kiếm theo giá                    ◄►");
            System.out.println("◄►                    0. Quay lại                             ◄►");
            System.out.println("◄►                                                            ◄►");
            System.out.println("◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►◄►");
            System.out.println();

            System.out.print("Chọn\t➨ ");
            try {
              choose =scanner.nextLine();
            } catch (Exception e) {
            }
            switch (choose) {
                case "1":
                    searchById();
                    break;
                case "2":
                    searchByName();
                    break;
                case "3":
                    searchByPrice();
                    break;
                case "0":
                    Menu.menuProduct();
                    isChoice = false;
                    break;
                default:
                    System.out.println("Chưa hợp lệ! Xin vui lòng nhập lại!");
            }

        } while (isChoice);
    }


    public static void searchById() {
        List<Product> list = productService.getItem();
        int count = 0;
//        System.out.println();
        System.out.print("Nhập ID sản phẩm cần tìm kiếm : ");
        try {
            int search = Integer.parseInt(scanner.nextLine());
            System.out.println("Kết quả :  '" + search + "' là : ");
            System.out.println("╔══════════════════════════════════════════════════════════════════════════════════╗");
            System.out.printf("║ %-20s %-30s %-18s %-10s\n", "Id", "Tên Sản Phẩm", "Giá: ", "Số lượng  ║");
            for (Product product : list) {
                if (product.getId() == search) {
                    count++;
                    System.out.printf("║ %-20s %-30s %-18s %-10s║\n", product.getId(),
                            product.getName(),product.getQuantity(), formater.format(product.getPrice()));

                }

            }
            System.out.println("╚══════════════════════════════════════════════════════════════════════════════════╝");
            showReturnSearch(count);

        } catch (Exception e) {
            System.out.println("Chưa hợp lệ! Xin vui lòng nhập lại!");
        }
    }

    public static void searchByName() {
        List<Product> list = productService.getItem();
        int count = 0;
        System.out.println();
        System.out.print("Nhập tên sản phẩm cần tìm kiếm : ");
        String search = scanner.nextLine();
        System.out.println("Kết quả tìm kiếm của từ khóa '" + search + "' là : ");
        search = search.toLowerCase();
        System.out.println("╔══════════════════════════════════════════════════════════════════════════════════╗");
        System.out.printf("║ %-18s %-20s %-18s %-20s\n", "ID", "TÊN SẢN PHẨM", "SỐ LƯỢNG", "GIÁ (VND)             ║");
        for (Product product : list) {
            if (product.getName().toLowerCase().contains(search)) {
                count++;
                System.out.printf("║ %-18s %-20s %-18s %-20s  ║\n", product.getId(), product.getName(),
                        product.getQuantity(), formater.format(product.getPrice()));
            }
        }
        System.out.println("╚══════════════════════════════════════════════════════════════════════════════════╝");
        showReturnSearch(count);
    }

    public static void searchByPrice() {
        List<Product> list = productService.getItem();
        int count = 0;
        System.out.println();
        System.out.print("Nhập giá sản phẩm cần tìm kiếm : ");
        double search = Double.parseDouble(scanner.nextLine());
        System.out.println("Kết quả tìm kiếm của từ khóa '" + search + "' là : ");
        System.out.println("╔══════════════════════════════════════════════════════════════════════════════════╗");
        System.out.printf("║ %-18s %-20s %-18s %-20s\n", "ID", "TÊN SẢN PHẨM", "SỐ LƯỢNG", "GIÁ (VND)             ║");
        for (Product product : list) {
            if (product.getPrice() == search) {
                count++;
                System.out.printf("║ %-18s %-20s %-18s %-20s  ║\n", product.getId(), product.getName(),
                        product.getQuantity(), formater.format(product.getPrice()));
            }
        }
        System.out.println("╚══════════════════════════════════════════════════════════════════════════════════╝");
        showReturnSearch(count);
    }

    public static void showReturnSearch(int count) {
        System.out.println("Có '" + count + "' sản phẩm được tìm thấy!");
        char press = ' ';
        boolean isChoice;
        System.out.println();
        do {
            System.out.print("Nhấn 'c' để về menu tìm kiếm! ➨ ");
            try {
                press = scanner.nextLine().charAt(0);
            } catch (Exception e) {
                press = ' ';
            }
            switch (press) {
                case 'r':
                case 'c': {
                    SearchMenu.searchMenu();
                    isChoice = false;
                    break;
                }
                default:
                    isChoice = true;
            }
        } while (isChoice);
    }
}
