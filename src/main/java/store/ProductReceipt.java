package store;

import java.text.NumberFormat;
import java.util.Locale;

public class ProductReceipt {

    private String name;
    private int count;
    private int price;
    private int freebie;
    private int freebiePrice;

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

    public ProductReceipt(Product product, int count, int totalPrice, int freebie, int freebiePrice) {
        this.name = product.getName();
        this.count = count;
        this.price = totalPrice;
        this.freebie = freebie;
        this.freebiePrice = freebiePrice;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public int getPrice() {
        return price;
    }

    public int getFreebie() {
        return freebie;
    }

    public int getFreebiePrice() {
        return freebiePrice;
    }

    public void modify(ProductReceipt productReceipt) {
        this.name = productReceipt.name;
        this.count += productReceipt.count;
        this.price += productReceipt.price;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name);
        stringBuilder.append("\t");
        stringBuilder.append("\t");
        stringBuilder.append(converyPriceString(count + freebie));
        stringBuilder.append("\t");
        stringBuilder.append(converyPriceString(price));
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }

    public String toStringFreebie() {
        StringBuilder stringBuilder = new StringBuilder();
        if (freebie == 0) {
            return stringBuilder.toString();
        }
        stringBuilder.append(name);
        stringBuilder.append("\t");
        stringBuilder.append("\t");
        stringBuilder.append(freebie);
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }

    private String converyPriceString(int price) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.KOREA);
        return numberFormat.format(price);
    }
}
