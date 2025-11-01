package lotto.validator;

import lotto.domain.Lotto;

public class LottoValidator {
    public static void validateBonusNotDuplicate(Lotto winningLotto, int bonusNumber){
        if (winningLotto.getNumbers().contains(bonusNumber)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }
}
