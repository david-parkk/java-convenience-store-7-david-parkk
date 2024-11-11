package store;

import java.time.LocalDateTime;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class PromotionCashierTest {

    @Test
    void 구입한_날짜가_프로모션_날짜_범위에_있어야_적용할_수_있다() {
        //given
        Product product = new Product("물품", 2000);
        LocalDateTime startTime = LocalDateTime.of(2024, 11, 10, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(2024, 11, 20, 23, 59);
        PromotionCashier promotionCashier = new PromotionCashier(product,
                12, "프로모션1", 3, startTime, endTime);

        //when
        LocalDateTime currentTime = LocalDateTime.of(2024, 11, 15, 0, 0);
        ProductReceipt productReceipt = promotionCashier.buy(10, currentTime);

        //then
        Assertions.assertThat(productReceipt.getCount()).isEqualTo(8);
    }

    @Test
    void 구입한_날짜가_프로모션_날짜_범위에_없으면_적용할_수_없다() {
        //given
        Product product = new Product("물품", 2000);
        LocalDateTime startTime = LocalDateTime.of(2024, 11, 10, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(2024, 11, 20, 23, 59);
        PromotionCashier promotionCashier = new PromotionCashier(product,
                12, "프로모션1", 3, startTime, endTime);

        //when
        LocalDateTime currentTime = LocalDateTime.of(2024, 11, 9, 0, 0);

        //then
        Assertions.assertThatThrownBy(() -> {
                    ProductReceipt productReceipt = promotionCashier.buy(10, currentTime);
                })
                .isInstanceOf(IllegalArgumentException.class);
    }
}
