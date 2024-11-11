package store;

import store.io.InputView;
import store.io.OutputView;

public class Kiosk {

    private InputView inputView;
    private OutputView outputView;
    private TotalCashier totalCashier;

    public Kiosk(InputView inputView, OutputView outputView, TotalCashier totalCashier) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.totalCashier = totalCashier;
    }

    public void start() {
        do {
            TotalOrder totalOrder = inputView.readBuyInput(totalCashier);

            if (checkOverOrder(totalOrder)) {
                continue;
            }

            checkAdditionalPromotionDetail(totalOrder);
            boolean isMembership = inputView.readMembership();

            outputView.printReceipt(totalCashier.buyProduct(totalOrder, isMembership));

        } while (!inputView.readFinishInput());
    }

    private boolean checkOverOrder(TotalOrder totalOrder) {
        if (!totalCashier.checkOverOrder(totalOrder)) {
            outputView.printOverException();
            return true;
        }
        return false;
    }

    private void checkAdditionalPromotionDetail(TotalOrder totalOrder) {
        totalCashier.checkPromotion(totalOrder);

    }
}
