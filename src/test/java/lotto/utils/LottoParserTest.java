package lotto.utils;

import lotto.domain.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class LottoParserTest {

    @Test
    void 문자열을_Lotto객체로_변환한다() {
        // given
        String input = "1, 2, 3, 4, 5, 6";

        // when
        Lotto lotto = LottoParser.stringToLotto(input);

        // then
        assertThat(lotto.getNumbers()).containsExactly(1, 2, 3, 4, 5, 6);
    }

}
