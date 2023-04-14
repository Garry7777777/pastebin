package com.skypro.pastebin.enums;

import java.time.temporal.ChronoUnit;

public enum Expiration {
    Never(1000L, ChronoUnit.DAYS),
    Ten_minutes(10L, ChronoUnit.MINUTES),
    One_hour(1L, ChronoUnit.HOURS),
    Tree_hours(3L, ChronoUnit.HOURS),
    One_day(1L, ChronoUnit.DAYS),
    One_week(7L, ChronoUnit.DAYS),
    One_month(30L, ChronoUnit.DAYS);

    public final long timeUnit;
    public final ChronoUnit chronoUnit;

    Expiration(long timeUnit, ChronoUnit chronoUnit) {
        this.timeUnit = timeUnit;
        this.chronoUnit = chronoUnit;
    }
}
