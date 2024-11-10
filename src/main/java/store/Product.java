package store;

public class Product {

    private String name;
    private int price;
    private int quantity;

    public Product(String name, int price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public int buy(int count) {
        this.quantity -= count;
        return price * count;
    }

    public int getQuantity() {
        return quantity;
    }
}
