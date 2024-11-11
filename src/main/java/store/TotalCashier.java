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
        int discount = cashierPair.checkPermitNoPromotion(order.getCount());
        if (discount > 0) {
            order.disCount(discount);
        }
    }

}
