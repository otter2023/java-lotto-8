package lotto.domain;

import java.util.HashSet;
import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    public Rank calculateRank(Lotto winningLotto, int bonusNumber) {
        int matchCount = matchCount(winningLotto);
        boolean hasBonus = containNumber(bonusNumber);
        return Rank.of(matchCount, hasBonus);
    }

    private int matchCount(Lotto winningLotto) {
        return (int) numbers.stream()
                .filter(winningLotto::containNumber)
                .count();
        /* 하단의 코드와 동일, 단 하단의 코드는 depth = 3
        int count = 0;
        for (int number : numbers) {
            if (winningLotto.containNumber(number)){
                count++;
            }
        }
        return count;
         */
    }

    // numbers가 private이므로 캡슐화를 지키기 위한 메소드
    private boolean containNumber(int number) {
        return numbers.contains(number);
    }

    private void validate(List<Integer> numbers) {
        validateCount(numbers);
        validateDuplicate(numbers);
        validateRange(numbers);
    }

    private void validateCount(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
    }

    private void validateDuplicate(List<Integer> numbers) {
        if (new HashSet<>(numbers).size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 중복될 수 없습니다.");
        }
    }

    private void validateRange(List<Integer> numbers) {
        for (int number : numbers) {
            if (number < 1 || number > 45) {
                throw new IllegalArgumentException("[ERROR] 로또 번호는 1~45 사이여야 합니다.");
            }
        }
    }
}
