package io.ehdev.android.drivingtime.backend;

import org.joda.time.Period;
import org.joda.time.Duration;

public class StringHelper {

    public static String getPeriodAsString(long remainingTime){

        remainingTime = remainingTime < 0 ? 0 : remainingTime;
        Period period = new Period(remainingTime);
        return getPeriodAsString(period);
    }

    public static String getPeriodAsString(Period remainingTime){
        remainingTime = remainingTime.toStandardDuration().compareTo(Duration.ZERO) < 0 ? new Period(0) : remainingTime;
        return String.format("%d:%02d", remainingTime.getHours(), remainingTime.getMinutes());
    }
}
