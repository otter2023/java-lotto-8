package lotto.view;


import camp.nextstep.edu.missionutils.Console;

public class LottoInputView {

    public String amount() {
        System.out.println("구입금액을 입력해 주세요.");
        return Console.readLine();
    }

    public String winningNumbers(){
        System.out.println("당첨 번호를 입력해 주세요.");
        return Console.readLine();
    }

    public String bonusNumber(){
        System.out.println("보너스 번호를 입력해 주세요.");
        return Console.readLine();
    }

}
