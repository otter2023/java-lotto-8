package utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ParserTest {

    @Test
    @DisplayName("정상적인 숫자 문자열은 int로 변환된다")
    void stringToInt_success() {
        // given
        String input = "8000";

        // when
        int result = Parser.stringToInt(input);

        // then
        assertThat(result).isEqualTo(8000);
    }

    @Test
    @DisplayName("문자열 양 끝의 공백은 무시하고 변환된다")
    void stringToInt_trim_success() {
        String input = "   1000   ";

        int result = Parser.stringToInt(input);

        assertThat(result).isEqualTo(1000);
    }

    @Test
    @DisplayName("쉼표(,)가 포함된 문자열은 변환에 실패한다")
    void stringToInt_comma_fail() {
        String input = "8,000";

        assertThatThrownBy(() -> Parser.stringToInt(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("정수여야");
    }

    @Test
    @DisplayName("비어 있는 문자열이면 예외가 발생한다")
    void stringToInt_empty_fail() {
        String input = "   ";

        assertThatThrownBy(() -> Parser.stringToInt(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("비어");
    }

    @Test
    @DisplayName("문자가 포함된 문자열이면 예외가 발생한다")
    void stringToInt_invalid_fail() {
        String input = "123원";

        assertThatThrownBy(() -> Parser.stringToInt(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("정수여야");
    }
}
