package coffeshop.view;

import coffeshop.menu.Menu;
import coffeshop.service.ProductService;
import coffeshop.model.Product;
import sort_service.*;

import java.util.List;
import java.util.Scanner;

public class MenuSort {
    private static Scanner scanner = new Scanner(System.in);
    static ProductView productView = new ProductView();
    static ProductService productService = new ProductService();
    static List<Product> productsList;


    public MenuSort() {
        productsList = productService.getItem();
    }

    public static void sortMenu() {
        System.out.println("♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫");
        System.out.println("♫                      HIỂN THỊ SẮP XẾP                           ♫");
        System.out.println("♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫");
        System.out.println("♫                                                                 ♫");
        System.out.println("♫                 1. Hiển thị sắp xếp theo ID                     ♫");
        System.out.println("♫                 2. Hiển thị sắp xếp theo tên sản phẩm           ♫");
        System.out.println("♫                 3. Hiển thị sắp xếp theo giá                    ♫");
        System.out.println("♫                 0. Quay lại                                     ♫");
        System.out.println("♫                                                                 ♫");
        System.out.println("♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫♫");
        System.out.print("➨ \t");
    }

    public static void option() {
        boolean flag = true;
        String choice;
        do {
            sortMenu();
            choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    showSortById();
                    break;
                case "2":
                    showSortByName();
                    break;
                case "3":
                    showSortByPrice();
                    break;
                case "0":
                    Menu.menuProduct();
                    break;
                default:
                    System.out.println("Không hợp lệ, vui lòng nhập lại");
                    flag = false;
            }
        } while (!flag);
    }


    public static void showSortByPrice() {
        boolean flag = true;
        String choice;
        do {
            System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
            System.out.println("■           SẮP XẾP THEO GIÁ SẢN PHẨM                    ■");
            System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
            System.out.println("■                                                        ■");
            System.out.println("■            1. Theo thứ tự từ tăng dần                  ■");
            System.out.println("■            2. Theo thứ tự từ giảm dần                  ■");
            System.out.println("■            0. Quay lại                                 ■");
            System.out.println("■                                                        ■");
            System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
            System.out.println();
            System.out.print("Chọn chức năng :");
            try {
                choice = scanner.nextLine();
                switch (choice) {
                    case "1":
                        List<Product> productList = productService.getItem();
                        System.out.println("Sắp xếp theo giá tăng dần");
                        SortByPriceASC sortByPriceASC = new SortByPriceASC();
                        productList.sort(sortByPriceASC);
                        productView.show(productList);
                        option();
                        break;
                    case "2":
                        List<Product> productsList = productService.getItem();
                        System.out.println("Sắp xếp theo giá giảm dần");
                        SortByPriceDESC sortByPriceDESC = new SortByPriceDESC();
                        productsList.sort(sortByPriceDESC);
                        productView.show(productsList);
                        option();
                        break;
                    case "0":
                        Menu.menuProduct();
                        break;
                    default:
                        System.out.println("Chọn lại!");
                        flag = false;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } while (!flag);
    }

    public static void showSortByName() {
        boolean flag = true;
        String choice;
        do {
            System.out.println("♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠");
            System.out.println("♠             SẮP XẾP THEO TÊN SẢN PHẨM                 ♠");
            System.out.println("♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠");
            System.out.println("♠                                                       ♠");
            System.out.println("♠           1. Theo thứ tự tên tăng dần                 ♠");
            System.out.println("♠           2. Theo thứ tự tên giảm dần                 ♠");
            System.out.println("♠           0. Quay lại                                 ♠");
            System.out.println("♠                                                       ♠");
            System.out.println("♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠");
            System.out.println();
            System.out.print("Chọn chức năng :");
            try {
                choice = scanner.nextLine();
                switch (choice) {
                    case "1":
                        List<Product> productList = productService.getItem();
                        System.out.println("Sắp xếp theo tên tăng dần");
                        SortByNameASC sortByNameASC = new SortByNameASC();
                        productList.sort(sortByNameASC);
                        productView.show(productList);
                        option();
                        break;
                    case "2":
                        List<Product> productsList = productService.getItem();
                        System.out.println("Sắp xếp theo tên giảm dần");
                        SortByNameDESC sortByNameDESC = new SortByNameDESC();
                        productsList.sort(sortByNameDESC);
                        productView.show(productsList);
                        option();
                        break;
                    case "0":
                        Menu.menuProduct();
                        break;
                    default:
                        System.out.println("Chọn lại!");
                        flag = false;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } while (!flag);
    }

    public static void showSortById() {
        boolean flag = true;
        String choice;
        do {
            System.out.println("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
            System.out.println("▬              SẮP XẾP THEO ID SẢN PHẨM                  ▬");
            System.out.println("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
            System.out.println("▬                                                        ▬");
            System.out.println("▬              1. Theo thứ tự từ tăng dần                ▬");
            System.out.println("▬              2. Theo thứ tự từ giảm dần                ▬");
            System.out.println("▬              0. Quay lại                               ▬");
            System.out.println("▬                                                        ▬");
            System.out.println("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
            System.out.println();
            System.out.print("Chọn chức năng:");
            try {
                choice = scanner.nextLine();
                switch (choice) {
                    case "1":
                        List<Product> productList = productService.getItem();
                        System.out.println("Sắp xếp theo ID tăng dần");
                        SortByIdASC sortByIDASC = new SortByIdASC();
                        productList.sort(sortByIDASC);
                        productView.show(productList);
                        option();
                        break;
                    case "2":
                        List<Product> productsList = productService.getItem();
                        System.out.println("Sắp xếp theo ID giảm dần");
                        SortByIdDESC sortByIDDESC = new SortByIdDESC();
                        productsList.sort(sortByIDDESC);
                        productView.show(productsList);
                        option();
                        break;
                    case "0":
                        Menu.menuProduct();
                        break;
                    default:
                        System.out.println("Chọn lại!");
                        flag = false;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } while (!flag);
    }
}
