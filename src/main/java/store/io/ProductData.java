package store.io;

public class ProductData {

    private String name;
    private int price;
    private int quantity;
    private PromotionData promotionData;

    public ProductData(String name, int price, int quantity, PromotionData
            promotionData) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.promotionData = promotionData;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public PromotionData getPromotionData() {
        return promotionData;
    }
}
