package utils;

public class Parser {

    public static int stringToInt(String input) {
        isNotNull(input);
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 해당 값은 정수여야 합니다.");
        }
    }

    public static void isNotNull(String input){
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 문자열이 비어 있습니다.");
        }
    }

    public static double round(double value, int scale) {
        double factor = Math.pow(10, scale);
        return Math.round(value * factor) / factor;
    }
}
