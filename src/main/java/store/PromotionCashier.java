package store;

import java.time.LocalDateTime;

public class PromotionCashier {

    private Product product;
    public int quantity;
    private int promotionCondition;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public PromotionCashier(Product product, int promotionCondition, LocalDateTime startTime,
                            LocalDateTime endTime) {
        this.product = product;
        this.promotionCondition = promotionCondition;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Message checkBuy(int count, LocalDateTime now) {
        if (now.isAfter(startTime) && now.isBefore(endTime)) {
            return Message.SUCCESS;
        }
        return Message.EXPIRED_PROMOTION;
    }

    public int buy(int count, LocalDateTime now) {
        int buyCount = count - (count % promotionCondition);
        quantity -= count;
        return product.buy(buyCount);
    }
}
