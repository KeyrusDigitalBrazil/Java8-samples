package br.com.keyrus.samples.java8.date;

import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/*
 * Java 8 version
 * https://github.com/adrianoreis/misc
 */
public class BusinessHourCalculator {
    public enum DayOfWeekLocal {
        SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY;
    }

    Map<DayOfWeekLocal, OpeningTime> openingTimes = new EnumMap<DayOfWeekLocal, OpeningTime>(DayOfWeekLocal.class); // opening
    // time
    // based
    // on
    // DayOfWeek
    // enum
    Map<String, OpeningTime> additionalOpeningTimes      = new HashMap<String, OpeningTime>(); // opening
                                                                                               // time
                                                                                               // based
                                                                                               // on
                                                                                               // formatted
                                                                                               // date
                                                                                               // like
                                                                                               // "2012-10-12"
    Set<String>              additionalDaysStoreIsClosed = new HashSet<String>();              // based
                                                                                               // on
                                                                                               // formatted
                                                                                               // date
                                                                                               // like
                                                                                               // "2012-10-12"

    public BusinessHourCalculator(String defaultOpeningTime, String defaultClosingTime) throws ParseException {
        init(defaultOpeningTime, defaultClosingTime);
    }

    public LocalDateTime calculateDeadline(long timeInterval, String desiredDropOffTimestamp) throws ParseException {
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        DateTimeFormatter sdf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd H:mm");
        LocalDateTime desiredDropOffDateTime = LocalDateTime.now();

        if (desiredDropOffDateTime != null) {
            try {
                desiredDropOffDateTime = LocalDateTime.parse(desiredDropOffTimestamp, sdf);
            } catch (DateTimeParseException e) {
                desiredDropOffDateTime = LocalDateTime.parse(desiredDropOffTimestamp, sdf2);
            }
        }

        desiredDropOffDateTime = getClosestValidBusinessDay(desiredDropOffDateTime);
        LocalDateTime openingHourForDesiredDropOffDay = getOpeningHourForDate(desiredDropOffDateTime);
        LocalDateTime closingHourForDesiredDropOffDay = getClosingHourForDate(desiredDropOffDateTime);

        if (desiredDropOffDateTime.isBefore(openingHourForDesiredDropOffDay)) {
            desiredDropOffDateTime = openingHourForDesiredDropOffDay;
        }

        if (desiredDropOffDateTime.isAfter(closingHourForDesiredDropOffDay)) {
            LocalDateTime calendar = closingHourForDesiredDropOffDay;
            calendar = calendar.plusDays(1);
            LocalDateTime closestValidBusinessDay = getClosestValidBusinessDay(calendar);
            desiredDropOffDateTime = getOpeningHourForDate(closestValidBusinessDay);

        }

        long diffInMiliseconds = closingHourForDesiredDropOffDay.atZone(ZoneId.systemDefault()).toInstant()
                .toEpochMilli() - desiredDropOffDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long diffInSeconds = TimeUnit.MILLISECONDS.toSeconds(diffInMiliseconds);
        diffInSeconds = diffInSeconds < 0 ? -diffInSeconds : diffInSeconds;

        if (diffInSeconds < timeInterval) {
            long carryOver = diffInSeconds - timeInterval;
            carryOver = carryOver < 0 ? -carryOver : carryOver;
            LocalDateTime calendar = desiredDropOffDateTime;
            calendar = calendar.plusDays(1);
            LocalDateTime closestValidBusinessDay = getClosestValidBusinessDay(calendar);
            LocalDateTime desiredDropOffDateOpeningTime = getOpeningHourForDate(closestValidBusinessDay);
            LocalDateTime desiredDropOffDateClosingTime = getClosingHourForDate(closestValidBusinessDay);
            while (true) {
                long businessHoursInSeconds = TimeUnit.MILLISECONDS.toSeconds(desiredDropOffDateClosingTime
                        .atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
                        - desiredDropOffDateOpeningTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
                if (carryOver > businessHoursInSeconds) {
                    carryOver = carryOver - businessHoursInSeconds;
                    calendar = calendar.plusDays(1);
                    closestValidBusinessDay = getClosestValidBusinessDay(calendar);
                    desiredDropOffDateOpeningTime = getOpeningHourForDate(closestValidBusinessDay);
                    desiredDropOffDateClosingTime = getClosingHourForDate(closestValidBusinessDay);
                } else {
                    timeInterval = carryOver;
                    desiredDropOffDateTime = desiredDropOffDateOpeningTime;
                    break;
                }
            }
        }
        LocalDateTime calendar = desiredDropOffDateTime;
        calendar = calendar.plusSeconds((int) timeInterval);
        return calendar;
    }

    private LocalDateTime getClosestValidBusinessDay(LocalDateTime desiredDropOffDateTime) {
        LocalDateTime calendar = desiredDropOffDateTime;
        while (isClosed(calendar)) {
            calendar = calendar.plusDays(1);
        }
        return calendar;
    }

    private boolean isClosed(LocalDateTime desiredDropOffDateTime) {
        LocalDateTime calendar = desiredDropOffDateTime;
        int dayOkWeek = calendar.getDayOfWeek().getValue();
        if (dayOkWeek == 7)
            dayOkWeek = 0;
        DayOfWeekLocal dayOfWeek = DayOfWeekLocal.values()[dayOkWeek];
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formatedLocalDateTime = sdf.format(desiredDropOffDateTime);
        if (additionalDaysStoreIsClosed.contains(formatedLocalDateTime) || !openingTimes.containsKey(dayOfWeek)) {
            return true;
        }
        return false;
    }

    public void setOpeningHours(DayOfWeekLocal dayOfWeek, String openingTime, String closingTime)
            throws ParseException {
        openingTimes.put(dayOfWeek, new OpeningTime(openingTime, closingTime));
    }

    public void setOpeningHours(String day, String openingTime, String closingTime) throws ParseException {
        additionalOpeningTimes.put(day, new OpeningTime(openingTime, closingTime));
    }

    public void setClosed(DayOfWeekLocal... daysOfWeek) {
        for (DayOfWeekLocal dayOfWeek : daysOfWeek) {
            openingTimes.remove(dayOfWeek);
        }
    }

    public void setClosed(String... days) {
        for (String day : days) {
            additionalDaysStoreIsClosed.add(day);
        }
    }

    private LocalDateTime getOpeningHourForDate(LocalDateTime aDate) {
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedLocalDateTime = sdf.format(aDate);
        LocalDateTime aDateCalendar = aDate;
        LocalDateTime openingTimeCalendar = LocalDateTime.now();
        if (additionalOpeningTimes.containsKey(formattedLocalDateTime)) {
            LocalDateTime openingTime = additionalOpeningTimes.get(formattedLocalDateTime).getStart();
            openingTimeCalendar = openingTime;
            aDateCalendar = aDateCalendar.withHour(openingTimeCalendar.getHour());
            aDateCalendar = aDateCalendar.withMinute(openingTimeCalendar.getMinute());
        } else {
            DayOfWeekLocal dayOfWeek = DayOfWeekLocal.values()[aDateCalendar.getDayOfWeek().getValue()];
            LocalDateTime openingTime = openingTimes.get(dayOfWeek).getStart();
            openingTimeCalendar = openingTime;
            aDateCalendar = aDateCalendar.withHour(openingTimeCalendar.getHour());
            aDateCalendar = aDateCalendar.withMinute(openingTimeCalendar.getMinute());
        }
        // the opening time for the requested date
        return aDateCalendar;
    }

    private LocalDateTime getClosingHourForDate(LocalDateTime aDate) {
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedLocalDateTime = sdf.format(aDate);
        LocalDateTime aDateCalendar = aDate;
        LocalDateTime closingTime;
        LocalDateTime closingTimeCalendar = LocalDateTime.now();
        if (additionalOpeningTimes.containsKey(formattedLocalDateTime)) {
            closingTime = additionalOpeningTimes.get(formattedLocalDateTime).getEnd();

        } else {
            DayOfWeekLocal dayOfWeek = DayOfWeekLocal.values()[aDateCalendar.getDayOfWeek().getValue()];
            closingTime = openingTimes.get(dayOfWeek).getEnd();
        }

        closingTimeCalendar = closingTime;
        aDateCalendar = aDateCalendar.withHour(closingTimeCalendar.getHour());
        aDateCalendar = aDateCalendar.withMinute(closingTimeCalendar.getMinute());
        // the closing time for the requested date
        return aDateCalendar;
    }

    private void init(String defaultOpeningTime, String defaultClosingTime) throws ParseException {
        for (DayOfWeekLocal d : DayOfWeekLocal.values()) {
            openingTimes.put(d, new OpeningTime(defaultOpeningTime, defaultClosingTime));
        }
    }

    private class OpeningTime {
        LocalTime         start;
        LocalTime         end;
        DateTimeFormatter sdf  = DateTimeFormatter.ofPattern("HH:mm");
        DateTimeFormatter sdf2 = DateTimeFormatter.ofPattern("H:mm");

        OpeningTime(String s, String e) throws ParseException {

            try {
                start = LocalTime.parse(s, sdf);
            } catch (DateTimeParseException pe) {
                start = LocalTime.parse(s, sdf2);
            }
            try {
                end = LocalTime.parse(e, sdf);
            } catch (DateTimeParseException pe) {
                end = LocalTime.parse(s, sdf2);
            }

        }

        LocalDateTime getStart() {
            Instant instant = start.atDate(LocalDate.now()).atZone(ZoneId.systemDefault()).toInstant();
            return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        }

        LocalDateTime getEnd() {
            Instant instant = end.atDate(LocalDate.now()).atZone(ZoneId.systemDefault()).toInstant();
            return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        }

    }

}
