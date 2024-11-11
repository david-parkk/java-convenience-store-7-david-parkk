package store;

import camp.nextstep.edu.missionutils.DateTimes;
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
        return cashierPair.buy(order.getCount(), DateTimes.now());
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        cashierMap.values().forEach(cashierPair -> {
            stringBuilder.append(cashierPair.toString());
        });
        return stringBuilder.toString();
    }

    public boolean checkOrder(TotalOrder totalOrder) {
        return totalOrder.getOrders().stream()
                .map(order -> {
                    CashierPair cashierPair = cashierMap.get(order.getName());
                    return cashierPair.check(order.getCount());
                })
                .allMatch(Boolean::booleanValue);


    }
}
