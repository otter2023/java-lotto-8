package lotto.view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThatCode;

class LottoInputViewTest {

    @Test
    void 구입금액이_0이하이면_예외발생() throws Exception {
        LottoInputView view = new LottoInputView();
        Method method = LottoInputView.class.getDeclaredMethod("validateIsPositive", String.class);
        method.setAccessible(true);

        assertThatThrownBy(() -> method.invoke(view, "0"))
                .hasRootCauseInstanceOf(IllegalArgumentException.class)
                .hasRootCauseMessage("[ERROR] 구입 금액 형식은 양수여야 합니다.");

        assertThatThrownBy(() -> method.invoke(view, "-1000"))
                .hasRootCauseInstanceOf(IllegalArgumentException.class)
                .hasRootCauseMessage("[ERROR] 구입 금액 형식은 양수여야 합니다.");
    }

    @Test
    void 공백은_허용() throws Exception {
        LottoInputView view = new LottoInputView();
        Method method = LottoInputView.class.getDeclaredMethod("validateWinningNumbersFormat", String.class);
        method.setAccessible(true);

        assertThatCode(() -> method.invoke(view, "1, 2, 3, 4, 5, 6"))
                .doesNotThrowAnyException();
    }

    @Test
    void 당첨번호_형식에_문자가_포함되면_예외() throws Exception {
        LottoInputView view = new LottoInputView();
        Method method = LottoInputView.class.getDeclaredMethod("validateWinningNumbersFormat", String.class);
        method.setAccessible(true);

        assertThatThrownBy(() -> method.invoke(view, "1, 2, a, 4, 5, 6"))
                .hasRootCauseInstanceOf(IllegalArgumentException.class)
                .hasRootCauseMessage("[ERROR] 당첨 번호는 숫자와 콤마가 번갈아 나와야 합니다.");
    }

    @Test
    void 당첨번호_형식에_콤마가_누락되면_예외() throws Exception {
        LottoInputView view = new LottoInputView();
        Method method = LottoInputView.class.getDeclaredMethod("validateWinningNumbersFormat", String.class);
        method.setAccessible(true);

        assertThatThrownBy(() -> method.invoke(view, "1 2,3,4,5,6"))
                .hasRootCauseInstanceOf(IllegalArgumentException.class)
                .hasRootCauseMessage("[ERROR] 당첨 번호는 숫자와 콤마가 번갈아 나와야 합니다.");
    }
}
