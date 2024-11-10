package store;

public class ProductCashier {

    private Product product;
    private int quantity;

    public ProductCashier(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
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
}
