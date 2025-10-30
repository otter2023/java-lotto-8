package lotto.domain;

import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
    }

    public Rank calculateRank(Lotto winningLotto, int bonusNumber) {
        int matchCount = matchCount(winningLotto);
        boolean hasBonus = containNumber(bonusNumber);
        return Rank.of(matchCount, hasBonus);
    }

    private boolean containNumber(int bonusNumber) {
    }

    private int matchCount(Lotto winningLotto) {
    }
}
