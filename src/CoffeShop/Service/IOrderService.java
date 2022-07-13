package CoffeShop.Service;

import CoffeShop.model.Order;

import java.util.List;

public interface IOrderService {
    //    private static OrderItemService orderItemService = new OrderItemService();
    List<Order> getOrders();

    Order getOrderByID(long id);

    void add(Order newOrder);

    void remove(Order order);

    void update();

    boolean existByPhone(String phone);

    boolean checkDuplicateFullName(String fullName);

    boolean exists(long id);
}
