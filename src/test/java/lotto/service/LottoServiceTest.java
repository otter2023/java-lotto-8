package lotto.service;

import lotto.domain.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class LottoServiceTest {

    private final LottoService service = new LottoService();

    @Test
    void 로또_정상생성() {
        // given
        Money money = new Money(8000);

        // when
        Lottos lottos = service.createLottos(money);

        // then
        assertThat(lottos).isNotNull();
        assertThat(lottos.getLottos().size()).isEqualTo(8);
    }

    @Test
    void 금액이_1000원단위가_아니면_예외발생() {
        assertThatThrownBy(() -> new Money(8500))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("1000으로 나누어 떨어져야 합니다");
    }

    @Test
    void 등수_계산_정상작동() {
        // given
        Lottos lottos = new Lottos(3); // 자동 생성
        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        int bonusNumber = 7;

        // when
        Map<Rank, Integer> result = service.calculateRanks(lottos, winningLotto, bonusNumber);

        // then
        assertThat(result).isNotNull();
        assertThat(result.keySet()).contains(Rank.MISS);
    }

    @Test
    void 등수별_집계_정상작동() {
        // given
        Lotto lotto1 = new Lotto(List.of(1, 2, 3, 10, 11, 12)); // 3개 일치 → 5등
        Lotto lotto2 = new Lotto(List.of(1, 2, 3, 4, 5, 7));    // 5개+보너스 → 2등
        Lotto lotto3 = new Lotto(List.of(1, 2, 3, 4, 5, 6));    // 6개 일치 → 1등

        Lottos lottos = new Lottos(0);
        List<Lotto> customList = List.of(lotto1, lotto2, lotto3);

        try {
            var field = Lottos.class.getDeclaredField("lottos");
            field.setAccessible(true);
            field.set(lottos, customList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        int bonusNumber = 7;

        // when
        Map<Rank, Integer> result = service.calculateRanks(lottos, winningLotto, bonusNumber);

        // then
        assertThat(result.get(Rank.FIRST)).isEqualTo(1);
        assertThat(result.get(Rank.SECOND)).isEqualTo(1);
        assertThat(result.get(Rank.FIFTH)).isEqualTo(1);
    }


}
