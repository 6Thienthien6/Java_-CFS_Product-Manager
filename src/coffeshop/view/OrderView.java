package coffeshop.view;

import coffeshop.menu.Menu;
import coffeshop.model.Order;
import coffeshop.model.OrderItem;
import coffeshop.model.Product;
import coffeshop.service.*;
import coffeshop.utils.ConvertUtils;
import coffeshop.utils.InstantUtils;
import coffeshop.utils.ValidationUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderView {
    private final IProductService productService;
    private final IOrderService orderService;
    private OrderItemService orderItemService = new OrderItemService();
    Scanner scanner = new Scanner(System.in);
    DecimalFormat format = new DecimalFormat("###,###,###" + " VNĐ");


    public OrderView() {
        productService = new ProductService();
        orderService = new OrderService();
        orderItemService = new OrderItemService();
    }

    public OrderItem addOrderItems(long orderID) {
        orderItemService.getOrderItem();
        ProductView productView = new ProductView();
        Long id=System.currentTimeMillis()/1000;
        List<Product> productList = productService.getItem();
        productView.show(productList);
        System.out.printf("Nhập ID sản phẩm:  \n➨ \t");
        int productID = Integer.parseInt(scanner.nextLine());
        while (!productService.exists(productID)) {
            System.out.println("ID không tồn tại ");
            System.out.print("Nhập id sản phẩm  \n➨ \t");
            productID = Integer.parseInt(scanner.nextLine());
        }

        Product product = productService.getProductByID(productID);
        double price = product.getPrice();
        int oldQuantity = product.getQuantity();
        System.out.print("Nhập số lượng: \n➨\t ");
        int quantity = Integer.parseInt(scanner.nextLine());
        while (!checkQuantityProduct(product, quantity)) {
            System.out.println("Vượt quá số lượng! Mời nhập lại");
            System.out.println("Nhập số lượng: \n➨ \t");
            quantity = Integer.parseInt(scanner.nextLine());
        }

        String productName = product.getName();
        double total = quantity * price;
        int currentQuantity = oldQuantity - quantity;
        product.setQuantity(currentQuantity);
        productService.update(product);

        OrderItem orderItem = new OrderItem(id, orderID, productID, productName, price, quantity, total);
        return orderItem;
    }


    public boolean checkQuantityProduct(Product product, int quantity) {
        return quantity <= product.getQuantity();
    }

    public void addOrder(String name, String phone, String address, ArrayList<OrderItem> list) {
//        list = new ArrayList<>();
//        do {
            try {
                orderService.getOrders();
                long orderID = System.currentTimeMillis() / 1000;
                System.out.println(orderID);
                OrderItem orderItem = addOrderItems(orderID);
                Order order = new Order(orderID, name, phone, address);
                orderItemService.add(orderItem);
                orderService.add(order);
                System.out.println("Bạn đã tạo đơn hàng thành công!");
                list.add(orderItem);
                boolean flag = true;
                do {
                    Menu.inputOrder();
                    String choice = scanner.nextLine();
                    switch (choice) {
                        case "a":
                            addOrder(name, phone, address, list);
                            break;
                        case "b":
                            ManagerOrderView.start();
                            break;
                        case "s":
                            showPaymentInfo(list, order);
                            break;
                        case "e":
                            Menu.exit();
                            System.exit(0);
                            break;
                        default:
                            System.out.println("Vui lòng nhập lại!");
                            flag = false;
                    }
                } while (!flag);
            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("Nhập sai! mời nhập lại");
            }
//        } while (true);

    }
    public void addOrderUs(String name, String phone, String address, ArrayList<OrderItem> list) {
//        list = new ArrayList<>();
//        do {
        try {
            orderService.getOrders();
            long orderID = System.currentTimeMillis() / 1000;
            System.out.println(orderID);
            OrderItem orderItem = addOrderItems(orderID);
            Order order = new Order(orderID, name, phone, address);
            orderItemService.add(orderItem);
            orderService.add(order);
            System.out.println("Bạn đã tạo đơn hàng thành công!");
            list.add(orderItem);
            boolean flag = true;
            do {
                Menu.inputOrder();
                String choice = scanner.nextLine();
                switch (choice) {
                    case "a":
                        addOrderUs(name, phone, address, list);
                        break;
                    case "b":
                        Menu.userOrder();
                        break;
                    case "s":
                        showPaymentInfoUs(list, order);
                        break;
                    case "e":
                        Menu.exit();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Vui lòng nhập lại!");
                        flag = false;
                }
            } while (!flag);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Nhập sai! mời nhập lại");
        }
//        } while (true);

    }

    public void enterInfoAddUs() {
        System.out.println("Nhập họ và tên: (vd: Nguyễn Văn An) ");
        System.out.print(" ➨ ");
        String name = scanner.nextLine();
        String namecheck;
        namecheck = ConvertUtils.removeAccent(name);
        while (!ValidationUtils.isNameValid(namecheck)) {
            System.out.println("Tên " + namecheck + " không đúng." + " Vui lòng nhập lại!" + " (Tên phải viết hoa chữ cái đầu)");
            System.out.println("Nhập tên: (Ví dụ: Le Minh Thang) ");
            System.out.print(" ➨ ");
            namecheck = scanner.nextLine();
        }

        System.out.print("Nhập số điện thoại: \n ➨ \t ");
        String phone = scanner.nextLine();
        while (!ValidationUtils.isPhoneValid(phone)) {
            System.out.print("Số " + phone + " chưa hợp lệ . Mời nhập lại (Số điện thoại bắt đầu bằng số 0) \n " +
                    " \t (SĐT có 10 hoặc 11 số) (vd: 0983452666) \n ➨ \t");
            phone = scanner.nextLine();
        }
        while (orderService.existByPhone(phone)) {
            System.out.println("Số này đã tồn tại, xin vui lòng nhập lại!");
            phone = scanner.nextLine();
        }


        System.out.print("Nhập địa chỉ: \n ➨ \t");
        String address = scanner.nextLine();
        while (!ValidationUtils.isAddressValid(address)) {
            System.out.println("Địa chỉ " + address + " chưa hợp lệ. Mời nhập lại  \n " +
                    "\t (vd: 23 Le Minh)");
            address = scanner.nextLine();
        }
        ArrayList<OrderItem> orderItems = new ArrayList<OrderItem>();
        addOrderUs(name, phone, address, orderItems);
    }
    public void enterInfoAdd() {
        System.out.println("Nhập họ và tên: (vd: Nguyễn Văn An) ");
        System.out.print(" ➨ ");
        String name = scanner.nextLine();
        String namecheck;
        namecheck = ConvertUtils.removeAccent(name);
        while (!ValidationUtils.isNameValid(namecheck)) {
            System.out.println("Tên " + namecheck + " không đúng." + " Vui lòng nhập lại!" + " (Tên phải viết hoa chữ cái đầu)");
            System.out.println("Nhập tên: (Ví dụ: Le Minh Thang) ");
            System.out.print(" ➨ ");
            namecheck = scanner.nextLine();
        }

        System.out.print("Nhập số điện thoại: \n ➨ \t ");
        String phone = scanner.nextLine();
        while (!ValidationUtils.isPhoneValid(phone)) {
            System.out.print("Số " + phone + " chưa hợp lệ . Mời nhập lại (Số điện thoại bắt đầu bằng số 0) \n " +
                    " \t (SĐT có 10 hoặc 11 số) (vd: 0983452666) \n ➨ \t");
            phone = scanner.nextLine();
        }
        while (orderService.existByPhone(phone)) {
            System.out.println("Số này đã tồn tại, xin vui lòng nhập lại!");
            phone = scanner.nextLine();
        }


        System.out.print("Nhập địa chỉ: \n ➨ \t");
        String address = scanner.nextLine();
        while (!ValidationUtils.isAddressValid(address)) {
            System.out.println("Địa chỉ " + address + " chưa hợp lệ. Mời nhập lại  \n " +
                    "\t (vd: 23 Le Minh)");
            address = scanner.nextLine();
        }
        ArrayList<OrderItem> orderItems = new ArrayList<OrderItem>();
        addOrder(name, phone, address, orderItems);
    }


    public void showPaymentInfo(List<OrderItem> list, Order order) {
        try {
            double amountOrder = 0;
            System.out.println("=======================================================================================" +
                    " Hóa đơn ====================================================================================");
            System.out.println();
            System.out.printf("%-20s %-20s %-25s %-15s %-12s %-25s %-15s %-20s %-20s\n",
                    "Tên",
                    "Địa chỉ",
                    "Số điện thoại",
                    "OrderID",
                    "ProductID",
                    "Tên Sản phẩm",
                    "Giá",
                    "Số lượng",
                    "Tổng");
            for (OrderItem orderItem : list) {
            System.out.printf("%-20s %-20s %-25s %-15s %-12s %-25s %-15s %-20s %-20s\n",
                    order.getName(),
                    order.getAddress(),
                    order.getPhone(),
                    order.getOrderID(),
                    orderItem.getProductID(),
                    orderItem.getProductName(),
                    format.format(orderItem.getPrice()),
                    orderItem.getQuantity(),
                    format.format(orderItem.getTotal()));
            System.out.print("Ngày tạo đơn: " + order.getCreatedAt());
            amountOrder += orderItem.getTotal();
            System.out.println();
                System.out.println("=======================================================================================" +
                        "====================================================================================");
            }
            System.out.println("\t\t\t\tTổng tiền: " + format.format(amountOrder));
            System.out.println("///////////////////////////////////////////////////////////////////////////////////////////" +
                    "////////////////////////////////////////////////////////////////////////////////////////");
            boolean flag = true;
            do {
                System.out.print("Nhấn 'c' để quay lại \nNhấn 'e' để thoát chương trình \n➨\t");
                String choice = scanner.nextLine();
                switch (choice) {
                    case "c":
                        ManagerOrderView.start();
                        break;
                    case "e":
                        Menu.exit();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Chưa hợp lệ, mời nhập lại");
                        flag = false;
                }
            } while (!flag);
        } catch (Exception ex) {
            ex.getStackTrace();
        }
    }
    public void showPaymentInfoUs(List<OrderItem> list, Order order) {
        try {
            double amountOrder = 0;
            System.out.println("=======================================================================================" +
                    " Hóa đơn ====================================================================================");
            System.out.println();
            System.out.printf("%-20s %-20s %-25s %-15s %-12s %-25s %-15s %-20s %-20s\n",
                    "Tên",
                    "Địa chỉ",
                    "Số điện thoại",
                    "OrderID",
                    "ProductID",
                    "Tên Sản phẩm",
                    "Giá",
                    "Số lượng",
                    "Tổng");
            for (OrderItem orderItem : list) {
                System.out.printf("%-20s %-20s %-25s %-15s %-12s %-25s %-15s %-20s %-20s\n",
                        order.getName(),
                        order.getAddress(),
                        order.getPhone(),
                        order.getOrderID(),
                        orderItem.getProductID(),
                        orderItem.getProductName(),
                        format.format(orderItem.getPrice()),
                        orderItem.getQuantity(),
                        format.format(orderItem.getTotal()));
                System.out.print("Ngày tạo đơn: " + order.getCreatedAt());
                amountOrder += orderItem.getTotal();
                System.out.println();
                System.out.println("=======================================================================================" +
                        "====================================================================================");
            }
            System.out.println("\t\t\t\tTổng tiền: " + format.format(amountOrder));
            System.out.println("///////////////////////////////////////////////////////////////////////////////////////////" +
                    "////////////////////////////////////////////////////////////////////////////////////////");
            boolean flag = true;
            do {
                System.out.print("Nhấn 'c' để quay lại \nNhấn 'e' để thoát chương trình \n➨\t");
                String choice = scanner.nextLine();
                switch (choice) {
                    case "c":
                        Menu menu = new Menu();
                        menu.userOrder();

                        break;
                    case "e":
                        Menu.exit();
//                        System.exit(0);
                        break;
                    default:
                        System.out.println("Chưa hợp lệ, mời nhập lại");
                        flag = false;
                }
            } while (!flag);
        } catch (Exception ex) {
            ex.getStackTrace();
        }
    }


    public void showAllOrder() {
        List<Order> orderList = orderService.getOrders();
        List<OrderItem> orderItems = orderItemService.getOrderItem();
        OrderItem newOI = new OrderItem();
        List<OrderItem> orders = orderItemService.getOrderItem();
        double total = 0;
        for (OrderItem orderItem : orders) {
            total += orderItem.getTotal();
        }

        try {
            System.out.println("####################################################################################" +
                    " DANH SÁCH ORDER @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            System.out.println();
            System.out.printf("%-12s %-13s %-23s %-15s %-12s %-25s %-15s %-20s %-20s\n",
                    " Ngày tạo",
                    "   Tên",
                    "    Địa chỉ",
                    "  OrderID",
                    "ProductID",
                    "  Tên Sản phẩm",
                    "  Giá",
                    "  Số lượng",
                    "Tổng");
            for (Order order : orderList) {
                for (OrderItem orderItem : orderItems) {
                    if (orderItem.getOrderID() == order.getOrderID()) {
                        newOI = orderItem;
                        break;
                    }
                }
                System.out.printf("%-12s %-13s %-23s %-15s %-12s %-25s %-20s %-10s %-20s\n",
                        InstantUtils.instantToFormat(order.getCreatedAt()),
                        order.getName(),
                        order.getAddress(),
                        order.getOrderID(),
                        newOI.getProductID(),
                        newOI.getProductName(),
                        format.format(newOI.getPrice()),
                        newOI.getQuantity(),
                        format.format(newOI.getTotal()));
            }
            System.out.println();
            System.out.printf("%150s\n", "Tổng doanh thu : " + format.format(total));
            System.out.println();
            System.out.println("##########################################################################################################" +
                    "##########################################################################");
            boolean flag = true;
            do {
                System.out.print("\tNhấn 'b' để quay lại \n\tNhấn 'e' để thoát chương trình \n\t➨ \t");
                String choice = scanner.nextLine();
                switch (choice) {
                    case "b":
                        ManagerOrderView.start();
                        break;
                    case "e":
                        Menu.exit();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Chưa hợp lệ, mời nhập lại");
                        flag = false;
                }
            } while (!flag);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
