package CoffeShop.View;

import CoffeShop.Menu.Menu;
import CoffeShop.Service.UserService;

import java.util.Scanner;

import static CoffeShop.Menu.Menu.loginMenu;

public class UserView {
    static Scanner scanner = new Scanner(System.in);
    private UserService userService;

//    public UserView() {
//        userService = new UserService();
//        loginUser();
//    }

    public void loginUser() {
        System.out.print("\tTên đăng nhập:\t");
        String username = scanner.nextLine();
        System.out.print("\tMật khẩu:\t");
        String password = scanner.nextLine();
        if(username.equals("User") && password.equals("User")){
            System.out.println("Đăng nhập thành công!");
            Menu menu = new Menu();
            menu.userOrder();
        }else {
            System.out.println("Tài khoản không đúng!");
            System.out.println("Nhập (1) để đăng nhập lại  ||  (2) để quay lại  ||  (3) để thoát");
            boolean check = false;
            do {
                String choose = scanner.nextLine();
                switch (choose) {
                    case "1":
                        loginUser();
                        break;
                    case "2":
                        loginMenu();
                        break;
                    default:
                        System.out.print("Sai chức năng, chọn lại: ");
                        check = true;
                        break;
                }
            } while (!check);
        }
    }
    public void addOrder() {
        OrderView orderView = new OrderView();
        orderView.enterInfoAdd();
    }
}
