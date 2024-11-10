package store;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class TotalCashier {

    private Map<String, CashierPair> cashierMap;

    public TotalCashier(Map<String, CashierPair> cashierMap) {
        this.cashierMap = cashierMap;
    }

    public TotalReceipt buyProduct(TotalOrder totalOrder) {
        List<ProductReceipt> productReceipts = totalOrder.getOrders().stream()
                .map(this::buy).toList();
        return new TotalReceipt(productReceipts);
    }

    public TotalReceipt buyProductByMembership(TotalOrder totalOrder) {
        TotalReceipt totalReceipt = this.buyProduct(totalOrder);
        totalReceipt.applyMembership();
        return totalReceipt;
    }

    private ProductReceipt buy(Order order) {
        CashierPair cashierPair = cashierMap.get(order.getName());
        return cashierPair.buy(order.getCount(), LocalDateTime.now());
    }
}
