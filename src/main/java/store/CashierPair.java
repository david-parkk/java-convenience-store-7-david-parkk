package store;

import java.time.LocalDateTime;

public class CashierPair {

    private PromotionCashier promotionCashier;
    private ProductCashier productCashier;

    public CashierPair(PromotionCashier promotionCashier, ProductCashier productCashier) {
        this.promotionCashier = promotionCashier;
        this.productCashier = productCashier;
    }

    public CashierPair(ProductCashier productCashier) {
        this.productCashier = productCashier;
    }

    public CashierPair(PromotionCashier promotionCashier) {
        this.promotionCashier = promotionCashier;
    }

    public boolean checkQuantity(int count) {
        if (getTotalQuantity() < count) {
            return false;
        }
        return true;
    }

    private void validate(int count) {
        if (getTotalQuantity() < count) {
            throw new IllegalArgumentException("[ERROR] 재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요.");
        }
    }

    public ProductReceipt buy(int count, LocalDateTime now) {
        validate(count);
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

    public void modify(ProductCashier productCashier) {
        this.productCashier = productCashier;
    }

    public void modify(PromotionCashier promotionCashier) {
        this.promotionCashier = promotionCashier;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (promotionCashier != null) {
            stringBuilder.append(promotionCashier.toString());
            stringBuilder.append("\n");
        }
        if (productCashier != null) {
            stringBuilder.append(productCashier.toString());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public void fillProductCashier() {
        if (productCashier == null) {
            productCashier = new ProductCashier(promotionCashier);
        }
    }

    public boolean checkPermitFreebie(int count) {
        if (promotionCashier != null) {
            return promotionCashier.isPermitFreebie(count);
        }
        return false;
    }

    public int checkPermitNoPromotion(int count) {
        if (promotionCashier != null) {
            return promotionCashier.isPermitNoPromotion(count);
        }
        return 0;
    }
}
