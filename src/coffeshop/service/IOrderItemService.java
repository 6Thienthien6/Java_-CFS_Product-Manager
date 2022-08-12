package coffeshop.service;

import coffeshop.model.OrderItem;

import java.util.List;

public interface IOrderItemService {
    List<OrderItem> getOrderItem();

    OrderItem getOrderItemByID(int id);

    void add(OrderItem newOI);

    void update(OrderItem newODI);
}
