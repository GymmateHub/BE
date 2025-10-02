package shared.common_utils;

public class PhoneValidator {
    private static final String PHONE_REGEX = "^\\+?[1-9]\\d{1,14}$";
    // Nigeria phone number regex example: ^\\+234\\d{10}$
    // This regex allows for an optional '+' at the start, followed by a country code and up to 15 digits.
    private static final String PHONE_REGEX_NIGERIA = "^\\+?234\\d{10}$";

    /**
     * Validates a phone number against the standard international format.
     *
     * @param phoneNumber the phone number to validate
     * @return true if the phone number is valid, false otherwise
     */
    public static boolean isValidPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            return false;
        }
        return phoneNumber.matches(PHONE_REGEX);
    }

    /**
     * Validates a Nigerian phone number.
     *
     * @param phoneNumber the phone number to validate
     * @return true if the phone number is valid for Nigeria, false otherwise
     */
    public static boolean isValidPhoneNumberNigeria(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            return false;
        }
        return phoneNumber.matches(PHONE_REGEX_NIGERIA);
    }
}
