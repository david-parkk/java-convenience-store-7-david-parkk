package store;

import java.time.LocalDateTime;

public class PromotionCashier {

    private Product product;
    private int quantity;
    private int promotionCondition;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public PromotionCashier(Product product, int quantity, int promotionCondition,
                            LocalDateTime startTime, LocalDateTime endTime) {
        this.product = product;
        this.quantity = quantity;
        this.promotionCondition = promotionCondition;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public boolean checkBuy(LocalDateTime now) {
        if (now.isAfter(startTime) && now.isBefore(endTime)) {
            return true;
        }
        return false;
    }

    public int getQuantity() {
        return quantity;
    }

    public ProductReceipt buy(int count, LocalDateTime now) {
        if (!checkBuy(now)) {
            throw new IllegalArgumentException("프로모션을 적용할 수 없는 시간입니다");
        }
        int freebie = calculateFreebie(count);
        quantity -= count;
        return new ProductReceipt(product, count - freebie, product.buy(count - freebie),
                freebie, product.buy(freebie));
    }

    private int calculateFreebie(int count) {
        return count / (promotionCondition + 1);
    }
}
