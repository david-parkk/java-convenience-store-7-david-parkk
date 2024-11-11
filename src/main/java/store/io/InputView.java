package store.io;

import camp.nextstep.edu.missionutils.Console;
import java.util.ArrayList;
import java.util.List;
import store.Order;
import store.TotalCashier;
import store.TotalOrder;

public class InputView {

    public TotalOrder readBuyInput(TotalCashier totalCashier) {
        System.out.println(totalCashier.toString());
        System.out.println("구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])");
        String input = Console.readLine();
        return parsingInput(input);
    }

    private TotalOrder parsingInput(String input) {
        List<Order> orders = new ArrayList<>();
        String[] entries = input.split(",");
        for (String entry : entries) {
            entry = entry.replaceAll("[\\[\\]]", "");
            String[] parts = entry.split("-");

            Order order = new Order(parts[0], Integer.parseInt(parts[1]));
            orders.add(order);
        }
        return new TotalOrder(orders);
    }

    public boolean readFinishInput() {
        System.out.println("감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)");
        String input = Console.readLine();
        if (input.equals("N")) {
            return true;
        }
        return false;
    }
}
