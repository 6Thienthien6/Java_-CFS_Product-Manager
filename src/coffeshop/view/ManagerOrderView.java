package coffeshop.view;

import coffeshop.menu.Menu;

import java.util.Scanner;

public class ManagerOrderView {
    static Scanner scanner = new Scanner(System.in);

    public static void start() {
        Menu.orderMenu();
        OrderView orderView = new OrderView();
        System.out.print("Chọn chức năng \n ➨ \t");
        try {
            boolean flag = true;

            do {
                String choice = scanner.nextLine();
                switch (choice) {
                    case "1" :
                        orderView.enterInfoAdd();
                        break;
                    case "2" :
                        orderView.showAllOrder();
                        break;
                    case "0" :
                        Menu.mainMenu();
                        break;
                    default:
                        System.out.println("Không hợp lệ, vui lòng nhập lại!");
                        flag = false;
                }
            }
            while (!flag);

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
