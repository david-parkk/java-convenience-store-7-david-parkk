package store;

public class ProductReceipt {

    private String name;
    private int count;
    private int price;
    private int freebie;

    public ProductReceipt() {
        this.name = "임시영수증";
        this.count = 0;
        this.price = 0;
        this.freebie = 0;
    }

    public ProductReceipt(Product product, int count, int totalPrice) {
        this.name = product.getName();
        this.count = count;
        this.price = totalPrice;
        this.freebie = 0;
    }

    public ProductReceipt(Product product, int count, int totalPrice, int freebie) {
        this.name = product.getName();
        this.count = count;
        this.price = totalPrice;
        this.freebie = freebie;
    }

    public int getCount() {
        return count;
    }

    public int getFreebie() {
        return freebie;
    }

    public void modify(ProductReceipt productReceipt) {
        this.count += productReceipt.count;
        this.price += productReceipt.price;
    }
}