package lotto.domain;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class LottoGeneratorTest {

    @Test
    void 로또_번호는_오름차순으로_정렬되어_있다() {
        Lotto lotto = LottoGenerator.generate();
        List<Integer> numbers = lotto.getNumbers();

        assertThat(numbers).isSorted();
    }
}
