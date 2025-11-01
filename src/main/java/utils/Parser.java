package utils;

public class Parser {

    public static int stringToInt(String input) {
        return Integer.parseInt(input);
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 해당 값은 정수여야 합니다.");
        }
    }

}
