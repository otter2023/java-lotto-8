package lotto.domain;

public class Money {
    private final int value;

    public Money(int value) {
        validate(value);
        this.value = value;
    }

    public Double calculateRateOfPrize(int prize){
        return (double)prize / value * 100;
    }

    private void validate(int value) {
        if (value % 1000 != 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 1000으로 나누어 떨어져야 합니다.");
        }
    }

    public int getValue() {
        return value;
    }
}
