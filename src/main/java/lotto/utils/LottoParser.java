package lotto.utils;

import lotto.domain.Lotto;
import utils.Parser;

import java.util.ArrayList;
import java.util.List;

public class LottoParser {

    public static Lotto stringToLotto(String input) {
        List<String> numbersByString = List.of(input.split(","));
        List<Integer> numbers = new ArrayList<>();

        for (String number : numbersByString) {
            int num = Parser.stringToInt(number);
            numbers.add(num);
        }

        return new Lotto(numbers);
    }

}
