package store;

public class ProductCashier {

    private Product product;
    private int quantity;

    public ProductCashier(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public ProductCashier(PromotionCashier promotionCashier) {
        this.product = promotionCashier.getProduct();
        this.quantity = 0;
    }

    public boolean checkBuy(int count) {
        if (count > quantity) {
            return false;
        }
        return true;
    }

    public int getQuantity() {
        return quantity;
    }

    public ProductReceipt buy(int count) {
        quantity -= count;
        return new ProductReceipt(product, count, product.buy(count));
    }

    private String getQuantityString() {
        if (quantity == 0) {
            return "재고 없음";
        }
        return String.valueOf(quantity);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(product.toString());
        stringBuilder.append(" ");
        stringBuilder.append(getQuantityString());
        stringBuilder.append("개");
        return stringBuilder.toString();
    }
}
