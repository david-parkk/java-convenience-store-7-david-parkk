package store;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import store.io.ProductData;

public class CashierFactory {

    private Map<String, CashierPair> cashierMap = new HashMap<>();

    public TotalCashier makeTotalCashier(List<ProductData> productDatas) {
        Map<String, CashierPair> cashierPairMap = makeCashiers(productDatas);
        return new TotalCashier(cashierPairMap);
    }

    public Map<String, CashierPair> makeCashiers(List<ProductData> productDatas) {
        productDatas.forEach(productData -> {
            CashierPair cashierPair = makeCashierPair(productData);
            cashierMap.put(productData.getName(), cashierPair);
        });
        return cashierMap;
    }

    private CashierPair makeCashierPair(ProductData productData) {
        if (productData.getPromotionData() != null) {
            PromotionCashier productCashier = makePromotionCashier(productData);
            return updateCashPair(productData.getName(), productCashier);
        }
        ProductCashier promotionCashier = makeProductCashier(productData);
        return updateCashPair(productData.getName(), promotionCashier);

    }

    private CashierPair updateCashPair(String name, ProductCashier productCashier) {
        CashierPair cashierPair = cashierMap.get(name);
        if (cashierPair != null) {
            cashierPair.modify(productCashier);
            return cashierPair;
        }
        return new CashierPair(productCashier);
    }

    private CashierPair updateCashPair(String name, PromotionCashier promotionCashier) {
        CashierPair cashierPair = cashierMap.get(name);
        if (cashierPair != null) {
            cashierPair.modify(promotionCashier);
            return cashierPair;
        }
        return new CashierPair(promotionCashier);
    }

    public Product makeProduct(ProductData productData) {
        return new Product(productData.getName(), productData.getPrice());
    }

    public ProductCashier makeProductCashier(ProductData productData) {
        Product product = makeProduct(productData);
        return new ProductCashier(product, productData.getQuantity());
    }

    public PromotionCashier makePromotionCashier(ProductData productData) {
        Product product = makeProduct(productData);
        return new PromotionCashier(product, productData.getQuantity()
                , productData.getPromotionData().getBuy(), productData.getPromotionData().getStartTime(),
                productData.getPromotionData()
                        .getEndTime());
    }
}
