package store;

import java.time.LocalDateTime;

public class PromotionCashier {

    private Product product;
    private int quantity;
    private String promotionName;
    private int promotionCondition;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public PromotionCashier(Product product, int quantity, String promotionName, int promotionCondition,
                            LocalDateTime startTime, LocalDateTime endTime) {
        this.product = product;
        this.quantity = quantity;
        this.promotionName = promotionName;
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

    public Product getProduct() {
        return product;
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

    private String getQuantityString() {
        if (quantity == 0) {
            return "재고 없음";
        }
        return String.valueOf(quantity) + "개";
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(product.toString());
        stringBuilder.append(" ");
        stringBuilder.append(getQuantityString());
        stringBuilder.append(" ");
        stringBuilder.append(promotionName);
        return stringBuilder.toString();
    }
}
