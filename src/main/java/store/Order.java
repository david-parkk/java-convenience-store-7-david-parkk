package store;

public class Order {

    private String name;
    private int count;

    public Order(String name, int count) {
        this.name = name;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public void addCount() {
        count++;
    }

    public void disCount(int discount) {
        count -= discount;
    }
}
