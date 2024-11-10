package store;

public class ProductCashier {

    private Product product;
    public int quantity;

    public ProductCashier(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Message checkBuy(int count) {
        if (count > quantity) {
            return Message.NO_QUANTITY;
        }
        return Message.SUCCESS;
    }

    public int buy(int count) {
        quantity -= count;
        return product.buy(count);
    }
}
