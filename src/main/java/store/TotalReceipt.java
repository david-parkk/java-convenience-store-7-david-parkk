package store;

import java.util.List;

public class TotalReceipt {

    private List<ProductReceipt> receipts;

    private int totalPrice;
    private int promotionDiscountPrice;
    private int membershipDiscountPrice;
    private int finalPrice;

    public TotalReceipt(List<ProductReceipt> receipts) {
        this.receipts = receipts;
        receipts.forEach(productReceipt -> {
            totalPrice += productReceipt.getPrice();
            promotionDiscountPrice += productReceipt.getFreebiePrice();
        });
    }

    public void applyMembership() {
        receipts.forEach(productReceipt -> {
            membershipDiscountPrice += productReceipt.getPrice() * 0.3;
        });
        membershipDiscountPrice = Math.min(8000, membershipDiscountPrice);
        finalPrice = totalPrice - membershipDiscountPrice;
    }

    public List<ProductReceipt> getReceipts() {
        return receipts;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public int getPromotionDiscountPrice() {
        return promotionDiscountPrice;
    }

    public int getMembershipDiscountPrice() {
        return membershipDiscountPrice;
    }

    public int getFinalPrice() {
        return finalPrice;
    }
}
