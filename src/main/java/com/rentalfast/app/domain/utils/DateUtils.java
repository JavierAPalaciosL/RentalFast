package com.rentalfast.app.domain.utils;

import com.rentalfast.app.domain.models.TimeToYearsMonthsWeeksDaysAndHours;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;


public class DateUtils {

    private static LocalDateTime toLocalDateTime(Date date) {
        return Instant.ofEpochMilli(date.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    public static TimeToYearsMonthsWeeksDaysAndHours breakdownInterval(
            LocalDateTime start,
            LocalDateTime end
    ) {
        if (end.isBefore(start)) {
            throw new IllegalArgumentException("endDate must be after startDate");
        }

        TimeToYearsMonthsWeeksDaysAndHours time = new TimeToYearsMonthsWeeksDaysAndHours();

        long years = start.until(end, ChronoUnit.YEARS);
        start = start.plusYears(years);
        time.setYears(years);

        long months = start.until(end, ChronoUnit.MONTHS);
        start = start.plusMonths(months);
        time.setMonths(months);

        long weeks = start.until(end, ChronoUnit.WEEKS);
        start = start.plusWeeks(weeks);
        time.setWeeks(weeks);

        long days = start.until(end, ChronoUnit.DAYS);
        start = start.plusDays(days);
        time.setDays(days);

        long hours = start.until(end, ChronoUnit.HOURS);
        time.setHours(hours);

        return time;
    }


}
