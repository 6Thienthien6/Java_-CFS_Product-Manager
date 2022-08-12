package coffeshop.menu;

import coffeshop.view.*;

import java.util.InputMismatchException;
import java.util.Scanner;


public class Menu {
    //    public static ProductService productService;
    static Scanner scanner = new Scanner(System.in);

    public static void adminlogin() {
        boolean cont = true;
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Tên đăng nhập: ");
            String username = scanner.nextLine();
            System.out.print("Mật khẩu: ");
            String password = scanner.nextLine();
            if (username.equals("admin") && password.equals("admin")) {
                System.out.println("Đăng nhập thành công");
                cont = false;
                mainMenu();
            } else if (username == null || password == null || !username.equals("admin") && !password.equals("admin")) {
                System.out.println("Tài khoản không đúng! Xin mời đăng nhập lại!!");

            }
        } while (cont);
    }

    public static void loginMenu() {
        boolean end = true;
        do {
            System.out.println("╔═════════════ MOOTE COFFEE ═══════════════╗");
            System.out.println("║                                          ║");
            System.out.println("║        1. Đăng nhập (Quản lý)            ║");
            System.out.println("║        2. Đăng nhập (Người dùng)         ║");
            System.out.println("║        3. Thoát chương trình             ║");
            System.out.println("╚══════════════════════════════════════════╝");
            System.out.print("chọn chức năng: ");
            Scanner scanner = new Scanner(System.in);
            String choose = scanner.nextLine();
            switch (choose) {
                case "1":
                    adminlogin();
                    break;
                case "2":
                    UserView userView = new UserView();
                    userView.loginUser();
                    break;
//                case "3":
//                    System.out.println("Xin lỗi, chúng tôi đang cập nhật chức năng này");
//                    System.out.println("Xin mời chọn lại!!!");
//                    break;
                case "3":
                    Menu.exit();
                default:
                    System.out.println("xin mời nhập lại: ");

            }
        } while (end);
    }

    public static void mainMenu() {

        try {
            boolean flag = true;
            do {
                System.out.println("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
                System.out.println("▬▬▬▬▬▬▬▬▬▬▬▬▬  MOOTE COFFEE  ▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
                System.out.println("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
                System.out.println("╔═════════════════════════════════════════════════════════╗");
                System.out.println("║                   1. Quản lí sản phẩm                   ║");
                System.out.println("║                   2. Quản lí đơn hàng                   ║");
                System.out.println("║                   3. Đăng Xuất                          ║");
                System.out.println("║                   4. Thoát chương trình                 ║");
                System.out.println("╚═════════════════════════════════════════════════════════╝");
                System.out.print("Chọn chức năng \n➨ \t");
                String num = scanner.nextLine();
                switch (num) {
                    case "1":
                        menuProduct();
                        break;
                    case "2":
                        ManagerOrderView.start();
                        break;
                    case "3":
                        Menu.loginMenu();
                    case "4":
                        Menu.exit();

                    default:
                        System.out.println("Không hợp lệ, xin vui lòng nhập lại!");
                        flag = false;
                }
            } while (!flag);
        } catch (InputMismatchException io) {
            io.printStackTrace();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void userOrder() {
        boolean flag = true;
        do {
            System.out.println("┌───────────────────────────────────────────────────┐");
            System.out.println("│                    MOOTE COFFE                    │");
            System.out.println("├───────────────────────────────────────────────────┤");
            System.out.println("│                                                   │");
            System.out.println("│                                                   │");
            System.out.println("│               1. Hiển thị Thức uống               │");
            System.out.println("│               2. Thêm thức uống                   │");
            System.out.println("│               3. Quay lại menu Chính              │");
            System.out.println("│               4. Đăng Xuất                        │");
            System.out.println("└───────────────────────────────────────────────────┘");
            System.out.print("Chọn chức năng \n➨ \t");
            String num = scanner.nextLine();
            switch (num) {
                case "1":
                    ProductView productView = new ProductView();
                    productView.showAllForUser();
                    break;
                case "2":
                    UserView userView = new UserView();
                    userView.addOrder();
                    break;
                case "3":
                    Menu.loginMenu();
                    break;
                case "4":
                    Menu.exit();
                    break;
                default:
                    System.out.println("Không hợp lệ, xin vui lòng nhập lại!");
                    flag = false;
                    break;
            }
        } while (!flag);
    }

    public static void menuProduct() {
        Scanner scanner = new Scanner(System.in);
        ProductView productView = new ProductView();
        String choose;
        boolean flag = true;
        try {
            do {
                System.out.println("╔══════════════════════════════════════════════════════════╗");
                System.out.println("║                      QUẢN LÝ SẢN PHẨM                    ║");
                System.out.println("╠══════════════════════════════════════════════════════════╣");
                System.out.println("║                                                          ║");
                System.out.println("║                   1. Thêm thức uống                      ║");
                System.out.println("║                   2. Sửa thông tin thức uống             ║");
                System.out.println("║                   3. Tìm kiếm thức uống                  ║");
                System.out.println("║                   4. Sắp xếp thức uống                   ║");
                System.out.println("║                   5. Xóa thức uống                       ║");
                System.out.println("║                   6. Hiển thị thức uống                  ║");
                System.out.println("║                   7. Quay về menu chính                  ║");
                System.out.println("║                   8. Đăng xuất                           ║");
                System.out.println("║                   0. Thoát chương trình                  ║");
                System.out.println("║                                                          ║");
                System.out.println("╚══════════════════════════════════════════════════════════╝ ");
                System.out.println();
                System.out.print("Chọn chức năng  \n➨ \t");
                choose = scanner.nextLine();
                switch (choose) {
                    case "1":
                        productView.addProduct();
                        break;
                    case "2":
                        productView.update();
                        break;
                    case "3":
                        SearchMenu.searchMenu();
                        break;
                    case "4":
                        MenuSort.option();
                        break;
                    case "5":
                        productView.remove();
                        break;
                    case "6":
                        productView.showAll();
                        break;
                    case "7":
                        Menu.mainMenu();
                        break;
                    case "8":
                        Menu.loginMenu();
                        break;
                    case "0":
                        Menu.exit();
                        break;
                    default:
                        System.out.println("Không hợp lệ, vui lòng nhập lại!");
                        flag = false;
                }
            } while (!flag);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void exit() {
        System.exit(0);
//        Exit exit = new Exit();
//        Thread thread1 = new Thread(exit);
//        thread1.start();
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.print("\t\t Bạn vừa thoát khỏi chương trình");
//        System.exit(0);
    }

    public static void inputUpdate() {
        System.out.println("◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊");
        System.out.println("◊              1. Cập nhật giá           ◊");
        System.out.println("◊              2. Cập nhật số lượng      ◊");
        System.out.println("◊              0. Quay lại               ◊");
        System.out.println("◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊◊");
        System.out.println("Chọn chức năng");
        System.out.print("➨ \t");
    }

    public static void removeConfirm() {
        System.out.println("☻☻☻☻☻☻☻☻☻☻☻☻☻☻☻☻☻☻☻☻☻☻☻☻☻☻☻☻☻☻☻☻☻☻☻☻");
        System.out.println("☻                BẠN CHẮC CHẮN MUỐN XÓA ?            ☻");
        System.out.println("☻☻☻☻☻☻☻☻☻☻☻☻☻☻☻☻☻☻☻☻☻☻☻☻☻☻☻☻☻☻☻☻☻☻☻☻");
        System.out.println("☻                                                    ☻");
        System.out.println("☻                 1. Nhấn y để xác nhận xóa .        ☻");
        System.out.println("☻                 2. Nhấn c để quay lại.             ☻");
        System.out.println("☻                                                    ☻");
        System.out.println("☻☻☻☻☻☻☻☻☻☻☻☻☻☻☻☻☻☻☻☻☻☻☻☻☻☻☻☻☻☻☻☻☻☻☻☻");
        System.out.printf("➨ \t");
    }

    public static void inputOrder() {
        System.out.println("☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼");
        System.out.println("☼                                                                 ☼");
        System.out.println("☼           1. Nhấn 'a' để tạo lại đơn hàng                       ☼");
        System.out.println("☼           2. Nhấn 'b' để quay lại                               ☼");
        System.out.println("☼           3. Nhấn 's' để in hóa đơn                             ☼");
        System.out.println("☼           4. Nhấn 'e' để thoát                                  ☼");
        System.out.println("☼                                                                 ☼");
        System.out.println("☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼");
        System.out.print("➨ \t");
    }

    public static void orderMenu() {
        System.out.println("♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥");
        System.out.println("♥♥                                    QUẢN LÝ ĐƠN HÀNG                                   ♥♥");
        System.out.println("♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥");
        System.out.println("♥                                                                                         ♥");
        System.out.println("♥                                 1. Tạo đơn hàng                                         ♥");
        System.out.println("♥                                 2. Xem danh sách đơn hàng                               ♥");
        System.out.println("♥                                 0. Quay lại                                             ♥");
        System.out.println("♥                                                                                         ♥");
        System.out.println("♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥");
    }

}
