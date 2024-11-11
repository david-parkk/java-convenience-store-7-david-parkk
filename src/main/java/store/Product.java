package store;

import java.text.NumberFormat;
import java.util.Locale;

public class Product {

    private String name;
    private int price;

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public int buy(int count) {
        return price * count;
    }

    public String getName() {
        return name;
    }

    public String getPriceString() {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.KOREA);
        return numberFormat.format(price) + "원";
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("- ");
        stringBuilder.append(name);
        stringBuilder.append(" ");
        stringBuilder.append(getPriceString());
        return stringBuilder.toString();
    }
}
