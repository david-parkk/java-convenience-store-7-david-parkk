package store;

import camp.nextstep.edu.missionutils.Console;
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

    public boolean isPermitFreebie(int count) {
        if (count > quantity) {
            return false;
        }
        count = count % (promotionCondition + 1);
        if (count == promotionCondition) {
            System.out.println("현재 " + product.getName() + "은(는) 1개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)");
            String input = Console.readLine();
            return input.equals("Y");
        }
        return false;
    }

    public int isPermitNoPromotion(int count) {
        if (count > quantity) {
            int extra = count - quantity;
            System.out.println("현재 " + product.getName() + " " + extra + "개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)");
            String input = Console.readLine();
            return extra;
        }
        return 0;
    }
}
