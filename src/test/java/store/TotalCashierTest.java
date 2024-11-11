package store;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class TotalCashierTest {


    @Test
    void 맴버쉽_회원은_프로모션_미적용_금액의_30퍼센트를_할인_받는다() {
        //given
        Product product = new Product("물품", 2000);
        LocalDateTime startTime = LocalDateTime.of(2024, 11, 10, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(2024, 11, 20, 23, 59);
        PromotionCashier promotionCashier = new PromotionCashier(product,
                3, "프로모션1", 3, startTime, endTime);
        ProductCashier productCashier = new ProductCashier(product, 12);
        CashierPair cashierPair = new CashierPair(promotionCashier, productCashier);

        Map<String, CashierPair> cashierPairMap = new HashMap<>();
        cashierPairMap.put(product.getName(), cashierPair);

        TotalCashier totalCashier = new TotalCashier(cashierPairMap);

        Order order = new Order(product.getName(), 3);
        TotalOrder totalOrder = new TotalOrder(List.of(order));

        //when
        TotalReceipt totalReceipt = totalCashier.buyProductByMembership(totalOrder);

        //then
        Assertions.assertThat(totalReceipt.getMembershipDiscountPrice()).isEqualTo(1800);
    }

    @Test
    void 최대_맴버쉽_할인의_최대한_한도는_8000원이다() {
        //given
        Product product = new Product("물품", 2000);
        LocalDateTime startTime = LocalDateTime.of(2024, 11, 10, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(2024, 11, 20, 23, 59);
        PromotionCashier promotionCashier = new PromotionCashier(product,
                1000, "프로모션1", 3, startTime, endTime);
        ProductCashier productCashier = new ProductCashier(product, 12);
        CashierPair cashierPair = new CashierPair(promotionCashier, productCashier);

        Map<String, CashierPair> cashierPairMap = new HashMap<>();
        cashierPairMap.put(product.getName(), cashierPair);

        TotalCashier totalCashier = new TotalCashier(cashierPairMap);

        Order order = new Order(product.getName(), 100);
        TotalOrder totalOrder = new TotalOrder(List.of(order));

        //when
        TotalReceipt totalReceipt = totalCashier.buyProductByMembership(totalOrder);

        //then
        Assertions.assertThat(totalReceipt.getMembershipDiscountPrice()).isEqualTo(8000);

    }
}
