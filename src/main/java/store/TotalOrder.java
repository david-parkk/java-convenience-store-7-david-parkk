package store;

import java.util.List;

public class TotalOrder {

    List<Order> orders;

    public TotalOrder(List<Order> orders) {
        this.orders = orders;
    }

    public List<Order> getOrders() {
        return orders;
    }
}
