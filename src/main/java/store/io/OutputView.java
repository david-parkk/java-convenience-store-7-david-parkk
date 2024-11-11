package store.io;

import store.TotalReceipt;

public class OutputView {

    public void printReceipt(TotalReceipt totalReceipt) {
        System.out.println(totalReceipt.toString());
    }

    public void printException() {
        System.out.println("[ERROR] 재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요.");
    }
}
