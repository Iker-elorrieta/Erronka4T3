package utils;

import java.sql.Date;
import java.util.Calendar;

public class DateConverter {

    public static Date toSqlDate(java.util.Date utilDate) {
        if (utilDate == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(utilDate);
        long time = cal.getTimeInMillis();
        return new Date(time);
    }

    public static java.util.Date toUtilDate(Date sqlDate) {
        if (sqlDate == null) {
            return null;
        }
        long time = sqlDate.getTime();
        return new java.util.Date(time);
    }
}

