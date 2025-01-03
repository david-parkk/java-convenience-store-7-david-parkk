package store;

import camp.nextstep.edu.missionutils.DateTimes;
import java.util.List;
import java.util.Map;

public class TotalCashier {

    private Map<String, CashierPair> cashierMap;

    public TotalCashier(Map<String, CashierPair> cashierMap) {
        this.cashierMap = cashierMap;
    }

    public TotalReceipt buyProduct(TotalOrder totalOrder, boolean isMembership) {
        List<ProductReceipt> productReceipts = totalOrder.getOrders().stream()
                .map(this::buy).toList();
        TotalReceipt totalReceipt = new TotalReceipt(productReceipts);

        if (isMembership) {
            totalReceipt.applyMembership();
        }
        return totalReceipt;
    }

    public TotalReceipt buyProductByMembership(TotalOrder totalOrder) {
        return this.buyProduct(totalOrder, true);
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

    public boolean checkOverOrder(TotalOrder totalOrder) {
        return totalOrder.getOrders().stream()
                .map(order -> {
                    CashierPair cashierPair = cashierMap.get(order.getName());
                    return cashierPair.checkQuantity(order.getCount());
                })
                .allMatch(Boolean::booleanValue);
    }

    public void checkPromotion(TotalOrder totalOrder) {
        totalOrder.getOrders()
                .forEach(order -> {
                    CashierPair cashierPair = cashierMap.get(order.getName());

                    checkPermitFreebie(cashierPair, order);
                    checkPermitNoPromotion(cashierPair, order);
                });
    }

    private void checkPermitFreebie(CashierPair cashierPair, Order order) {
        if (cashierPair.checkPermitFreebie(order.getCount())) {
            order.addCount();
        }
    }

    private void checkPermitNoPromotion(CashierPair cashierPair, Order order) {
        cashierPair.checkPermitNoPromotion(order.getCount());
    }

}
