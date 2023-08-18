package cooltu.lib4j.tools;

public class CharTool {

    public static boolean isUpper(char c) {
        return c >= 'A' && c <= 'Z';
    }

    public static boolean isLower(char c) {
        return c >= 'a' && c <= 'z';
    }

    public static boolean isLowerLine(char c) {
        return c == '_';
    }

    public static boolean isMiddleLine(char c) {
        return c == '-';
    }

    public static boolean isNumber(char c) {
        return c >= '0' && c <= '9';
    }

}
