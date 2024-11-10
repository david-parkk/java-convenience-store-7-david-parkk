package store;

import java.time.LocalDateTime;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class CashierPairTest {


    @Test
    void 프로모션_재고가_떨어지면_정가로_구매해야한다() {
        //given
        Product product = new Product("물품", 2000);
        LocalDateTime startTime = LocalDateTime.of(2024, 11, 10, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(2024, 11, 20, 23, 59);
        PromotionCashier promotionCashier = new PromotionCashier(product,
                1, 3, startTime, endTime);
        ProductCashier productCashier = new ProductCashier(product, 12);
        CashierPair cashierPair = new CashierPair(promotionCashier, productCashier);

        //when
        LocalDateTime currentTime = LocalDateTime.of(2024, 11, 15, 0, 0);
        ProductReceipt productReceipt = cashierPair.buy(5, currentTime);

        //then
        Assertions.assertThat(productReceipt.getCount()).isEqualTo(5);
    }

    @Test
    void 프로모션과_기본제품_구입결과를_합산한다() {
        //given
        Product product = new Product("물품", 2000);
        LocalDateTime startTime = LocalDateTime.of(2024, 11, 10, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(2024, 11, 20, 23, 59);
        PromotionCashier promotionCashier = new PromotionCashier(product,
                6, 3, startTime, endTime);
        ProductCashier productCashier = new ProductCashier(product, 12);
        CashierPair cashierPair = new CashierPair(promotionCashier, productCashier);

        //when
        LocalDateTime currentTime = LocalDateTime.of(2024, 11, 15, 0, 0);
        ProductReceipt productReceipt = cashierPair.buy(8, currentTime);

        //then
        Assertions.assertThat(productReceipt.getCount()).isEqualTo(7);
        Assertions.assertThat(productReceipt.getFreebie()).isEqualTo(1);
    }
}
