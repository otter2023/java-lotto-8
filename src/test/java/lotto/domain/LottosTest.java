package lotto.domain;

import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class LottosTest {

    @Test
    void 지정한_개수만큼_로또가_생성된다() {
        Lottos lottos = new Lottos(5);
        assertThat(lottos.getLottos()).hasSize(5);
    }

    @Test
    void 당첨_결과를_정확히_계산한다() {
        Lottos lottos = new Lottos(0);
        List<Lotto> 수동로또목록 = List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),  // 1등
                new Lotto(List.of(1, 2, 3, 4, 5, 7)),  // 2등
                new Lotto(List.of(1, 2, 3, 4, 8, 9)),  // 4등
                new Lotto(List.of(10, 11, 12, 13, 14, 15)) // 꽝
        );

        lottos.getLottos().clear();
        lottos.getLottos().addAll(수동로또목록);

        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        int bonusNumber = 7;

        Map<Rank, Integer> result = lottos.calculateRanks(winningLotto, bonusNumber);

        assertThat(result.get(Rank.FIRST)).isEqualTo(1);
        assertThat(result.get(Rank.SECOND)).isEqualTo(1);
        assertThat(result.get(Rank.FOURTH)).isEqualTo(1);
        assertThat(result.get(Rank.MISS)).isEqualTo(1);
    }

    @Test
    void 빈_결과에도_NPE가_발생하지_않는다() {
        Lottos lottos = new Lottos(1);
        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        int bonusNumber = 7;

        Map<Rank, Integer> result = lottos.calculateRanks(winningLotto, bonusNumber);

        assertThat(result.getOrDefault(Rank.FIRST, 0)).isEqualTo(0);
    }
}
