package store.io;

import camp.nextstep.edu.missionutils.Console;
import store.TotalCashier;

public class InputView {

    public String readBuyInput(TotalCashier totalCashier) {
        System.out.println(totalCashier.toString());
        return Console.readLine();
    }
}
