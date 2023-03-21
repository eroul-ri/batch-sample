package cc.sample.batch.common.utils;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DateUtil {
    static final String PTN_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    static final String PTN_YYYYMMDD_HHMM  = "yyyyMMdd HHmm";
    static final String yyyyMMdd           = "yyyyMMdd";
    static final String hhmm               = "HHmm";

    static final int WEEKDAYS = 7;

    public static String getYYYYMMDDHH24MISS(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ofPattern(PTN_YYYYMMDDHHMMSS));
    }

    public static String localDateToStr(LocalDate localDate) {
        return localDate.format(DateTimeFormatter.ofPattern(yyyyMMdd));// TODO
    }

    public static List<String> weekDateIds() {
        LocalDate toDay   = LocalDate.now();
        String[]  dateIds = new String[WEEKDAYS];
        for (int i = 0; i < dateIds.length; i++) {
            dateIds[i] = localDateToStr(toDay.plusDays(i));
        }
        return Arrays.asList(dateIds);
    }

    public static LocalDate stringDateTo(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(yyyyMMdd));
    }

    public static LocalDateTime stringDateTimeTo(String dateTime) {
        return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(PTN_YYYYMMDDHHMMSS));
    }

    public static LocalDateTime dateTimeStringToDateTime(String date, String time, String defaultDateTime) {
        LocalDateTime localDateTime;
        try {
            localDateTime = LocalDateTime.parse(date + " " + time, DateTimeFormatter.ofPattern(PTN_YYYYMMDD_HHMM));
        } catch (DateTimeParseException parseException) {
            localDateTime = LocalDateTime.parse(defaultDateTime, DateTimeFormatter.ofPattern(PTN_YYYYMMDD_HHMM));
            log.error("date time parse error : date[{}], time[{}]", date, time);
        }
        return localDateTime;
    }
}
