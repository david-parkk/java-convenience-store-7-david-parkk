package store;

import java.util.List;
import store.io.InputView;
import store.io.OutputView;
import store.io.ProductData;
import store.io.ResourceReader;

public class Application {
    public static void main(String[] args) {
        List<ProductData> productDatas = new ResourceReader().readData();
        TotalCashier totalCashier = new CashierFactory().makeTotalCashier(productDatas);

        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        do {
            TotalOrder totalOrder = inputView.readBuyInput(totalCashier);

            outputView.printReceipt(totalCashier.buyProduct(totalOrder));

        } while (!inputView.readFinishInput());
    }
}
