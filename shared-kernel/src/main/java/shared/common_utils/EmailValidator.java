package shared.common_utils;

public class EmailValidator {
    public static boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(emailRegex);
    }
    public static boolean isValidEmailOrNull(String email) {
        return email == null || isValidEmail(email);
    }
    public static boolean isValidEmailOrEmpty(String email) {
        return email == null || email.isEmpty() || isValidEmail(email);
    }
    public static boolean isValidEmailOrBlank(String email) {
        return email == null || email.isBlank() || isValidEmail(email);
    }

    public static boolean isValidEmailOrWhitespace(String email) {
        return email == null || email.trim().isEmpty() || isValidEmail(email);
    }

}
