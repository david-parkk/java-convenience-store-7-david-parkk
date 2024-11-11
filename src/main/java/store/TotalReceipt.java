package store;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class TotalReceipt {

    private List<ProductReceipt> receipts;

    private int totalPrice;
    private int totalCount;
    private int promotionDiscountPrice;
    private int membershipDiscountPrice;
    private int finalPrice;

    public TotalReceipt(List<ProductReceipt> receipts) {
        this.receipts = receipts;
        receipts.forEach(productReceipt -> {
            totalPrice += productReceipt.getPrice() + productReceipt.getFreebiePrice();
            promotionDiscountPrice += productReceipt.getFreebiePrice();
            totalCount += productReceipt.getCount() + productReceipt.getFreebie();
        });
        this.finalPrice = totalPrice - promotionDiscountPrice;
    }

    public void applyMembership() {
        receipts.forEach(productReceipt -> {
            if (productReceipt.getFreebiePrice() == 0) {
                membershipDiscountPrice += (productReceipt.getPrice()) * 0.3;
            }
        });
        membershipDiscountPrice = Math.min(8000, membershipDiscountPrice);
        finalPrice = totalPrice - promotionDiscountPrice - membershipDiscountPrice;
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

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("==============W 편의점================\n");
        stringBuilder.append("상품명\t\t수량 \t금액\n");
        receipts.forEach(stringBuilder::append);
        stringBuilder.append("=============증\t정===============\n");
        receipts.forEach(productReceipt ->
                stringBuilder.append(productReceipt.toStringFreebie()));
        stringBuilder.append("====================================\n");
        stringBuilder.append("총구매액\t\t" + totalCount + "\t" + converyPriceString(totalPrice, false) + "\n");
        stringBuilder.append("행사할인\t\t\t" + converyPriceString(promotionDiscountPrice, true) + "\n");
        stringBuilder.append("맴버십할인\t\t\t" + converyPriceString(membershipDiscountPrice, true) + "\n");
        stringBuilder.append("내실돈\t\t\t" + converyPriceString(finalPrice, false) + "\n");
        return stringBuilder.toString();
    }

    private String converyPriceString(int price, boolean isMinus) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.KOREA);
        if (isMinus) {
            return "-" + numberFormat.format(price);
        }
        return numberFormat.format(price);
    }
}
