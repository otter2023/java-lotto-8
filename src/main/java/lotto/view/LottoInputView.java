package lotto.view;


import camp.nextstep.edu.missionutils.Console;
import utils.Parser;

public class LottoInputView {

    private static final String WINNING_NUMBER_REGEX =
            "^" +             // 문자열 시작
            "\\s*" +          // 앞쪽 공백 허용
            "\\d+" +          // 첫 숫자 (1개 이상)
            "(\\s*,\\s*\\d+)*" + // 콤마(,) 앞뒤 공백 허용 + 숫자 반복
            "\\s*" +          // 마지막 공백 허용
            "$";              // 문자열 끝

    public String amount() {
        System.out.println("구입금액을 입력해 주세요.");
        String input = Console.readLine();
        validateIsPositive(input);
        return input;
    }

    public String winningNumbers() {
        System.out.println("당첨 번호를 입력해 주세요.");
        String input = Console.readLine();
        validateWinningNumbersFormat(input);
        return input;
    }

    public String bonusNumber() {
        System.out.println("보너스 번호를 입력해 주세요.");
        String input = Console.readLine();
        validateIsPositive(input);
        return input;
    }

    private void validateIsPositive(String amount) {
        if (Parser.stringToInt(amount) <= 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액 형식은 양수여야 합니다.");
        }
    }

    private void validateWinningNumbersFormat(String winningNumbers){
        if (!winningNumbers.matches(WINNING_NUMBER_REGEX)){
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 숫자와 콤마가 번갈아 나와야 합니다.");
        }
    }
}
