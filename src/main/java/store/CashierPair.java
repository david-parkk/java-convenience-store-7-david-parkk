package store;

import java.time.LocalDateTime;

public class CashierPair {

    private PromotionCashier promotionCashier;
    private ProductCashier productCashier;

    public CashierPair(PromotionCashier promotionCashier, ProductCashier productCashier) {
        this.promotionCashier = promotionCashier;
        this.productCashier = productCashier;
    }

    public ProductReceipt buy(int count, LocalDateTime now) {
        if (getTotalQuantity() < count) {
            throw new IllegalArgumentException("[ERROR] 재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요.");
        }

        ProductReceipt productReceipt = new ProductReceipt();
        if (checkPromotionCashierAvailable(now)) {
            int quantity = promotionCashier.getQuantity();
            productReceipt = promotionCashier.buy(Math.min(count, quantity), now);
        }

        int extraCount = count - productReceipt.getCount() - productReceipt.getFreebie();
        if (extraCount > 0) {
            productReceipt.modify(productCashier.buy(extraCount));
        }
        return productReceipt;
    }

    private int getTotalQuantity() {
        if (promotionCashier == null) {
            return productCashier.getQuantity();
        }
        return productCashier.getQuantity() + promotionCashier.getQuantity();
    }

    private boolean checkPromotionCashierAvailable(LocalDateTime now) {
        if (promotionCashier == null) {
            return false;
        }
        if (!promotionCashier.checkBuy(now)) {
            return false;
        }
        return true;
    }

}
