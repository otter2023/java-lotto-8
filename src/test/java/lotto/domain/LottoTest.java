package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LottoTest {
    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 로또번호가_범위를_벗어나면_예외발생() {
        assertThatThrownBy(() -> new Lotto(List.of(0, 1, 2, 3, 4, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 번호는 1~45 사이여야 합니다.");

        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 46)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 번호는 1~45 사이여야 합니다.");
    }

    @Test
    void 당첨번호와_3개_일치하면_5등() {
        Lotto 내로또 = new Lotto(List.of(1, 2, 3, 10, 11, 12));
        Lotto 당첨로또 = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        Rank 결과 = 내로또.calculateRank(당첨로또, 7);
        assertThat(결과).isEqualTo(Rank.FIFTH);
    }

    @Test
    void 당첨번호와_5개_일치하고_보너스번호_일치하면_2등() {
        Lotto 내로또 = new Lotto(List.of(1, 2, 3, 4, 5, 7)); // 보너스 포함
        Lotto 당첨로또 = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        int 보너스번호 = 7;

        Rank 결과 = 내로또.calculateRank(당첨로또, 보너스번호);
        assertThat(결과).isEqualTo(Rank.SECOND);
    }

    @Test
    void 당첨번호와_6개_모두_일치하면_1등() {
        Lotto 내로또 = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto 당첨로또 = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        Rank 결과 = 내로또.calculateRank(당첨로또, 7);
        assertThat(결과).isEqualTo(Rank.FIRST);
    }
}
