package shared.common_utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    public static String nowAsString() {
        return LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
    }

    public static String format(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ISO_DATE_TIME);
    }

    public static LocalDateTime parse(String dateTimeString) {
        return LocalDateTime.parse(dateTimeString, DateTimeFormatter.ISO_DATE_TIME);
    }
    public static String format(java.time.LocalDate date) {
        return date.format(DateTimeFormatter.ISO_DATE);
    }
    public static java.time.LocalDate parseDate(String dateString) {
        return java.time.LocalDate.parse(dateString, DateTimeFormatter.ISO_DATE);
    }
    public static String format(java.time.LocalTime time) {
        return time.format(DateTimeFormatter.ISO_TIME);
    }
    public static java.time.LocalTime parseTime(String timeString) {
        return java.time.LocalTime.parse(timeString, DateTimeFormatter.ISO_TIME);
    }
    public static String format(java.time.ZonedDateTime zonedDateTime) {
        return zonedDateTime.format(DateTimeFormatter.ISO_ZONED_DATE_TIME);
    }
    public static java.time.ZonedDateTime parseZonedDateTime(String zonedDateTimeString) {
        return java.time.ZonedDateTime.parse(zonedDateTimeString, DateTimeFormatter.ISO_ZONED_DATE_TIME);
    }
    public static String format(java.time.OffsetDateTime offsetDateTime) {
        return offsetDateTime.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }
    public static java.time.OffsetDateTime parseOffsetDateTime(String offsetDateTimeString) {
        return java.time.OffsetDateTime.parse(offsetDateTimeString, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }
    public static String format(java.time.Instant instant) {
        return instant.toString();
    }
}
