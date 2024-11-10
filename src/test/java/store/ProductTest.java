package store;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductTest {


    @Test
    void 물건을_구매_할_수_있다() {
        //given
        Product product = new Product("제품", 1000);

        //when
        int price = product.buy(2);

        //then
        Assertions.assertThat(price).isEqualTo(2000);
    }
}
