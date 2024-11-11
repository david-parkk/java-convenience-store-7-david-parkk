package store;

import java.util.List;
import store.io.ProductData;
import store.io.ResourceReader;

public class Application {
    public static void main(String[] args) {
        List<ProductData> productDatas = new ResourceReader().readData();
        TotalCashier totalCashier = new CashierFactory().makeTotalCashier(productDatas);

        
    }
}
