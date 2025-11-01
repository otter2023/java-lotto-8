package lotto.view;


import camp.nextstep.edu.missionutils.Console;
import utils.Parser;

public class LottoInputView {

    public String amount() {
        System.out.println("구입금액을 입력해 주세요.");
        String input = Console.readLine();
        validateIsInteger(input);
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
        validateIsInteger(input);
        validateIsPositive(input);
        return input;
    }

    private void validateIsInteger(String amount) {
        try {
            Integer.parseInt(amount);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 구입 금액 형식은 정수여야 합니다.");
        }
    }

    private void validateIsPositive(String amount) {
        if (Parser.stringToInt(amount) <= 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액 형식은 양수여야 합니다.");
        }
    }

    private void validateWinningNumbersFormat(String winningNumbers){
        if (!winningNumbers.matches("^\\d+(,\\d+)*$")){
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 숫자와 콤마가 번갈아 나와야 합니다.");
        }
    }
}
