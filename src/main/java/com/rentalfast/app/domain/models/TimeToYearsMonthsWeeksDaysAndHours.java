package com.rentalfast.app.domain.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TimeToYearsMonthsWeeksDaysAndHours {
    private long years;
    private long months;
    private long weeks;
    private long days;
    private long hours;
}
