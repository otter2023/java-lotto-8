package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class MoneyTest {

    @Test
    void 금액이_1000으로_나누어_떨어지지_않으면_예외발생() {
        assertThatThrownBy(() -> new Money(8500))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("구입 금액은 1000으로 나누어 떨어져야 합니다.");
    }

    @Test
    void 수익률이_정확히_계산된다() {
        Money money = new Money(8000);
        int prize = 5000; // 총 상금 5000원
        double rate = money.calculateRateOfPrize(prize);

        assertThat(rate).isEqualTo(62.5);
    }

    @Test
    void 수익률이_소수점_두번째자리까지_정확하다() {
        Money money = new Money(12000);
        int prize = 10000;
        double rate = money.calculateRateOfPrize(prize);

        assertThat(rate).isEqualTo(83.33333333333334); // 10000 / 12000 * 100
    }
}
