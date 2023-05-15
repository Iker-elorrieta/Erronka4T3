package utils;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateConverter {

    public static Date toSqlDate(java.util.Date utilDate) {
        if (utilDate == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(utilDate);
        long time = cal.getTimeInMillis();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String fechaFormateada = formato.format(new Date(time));
     
        return  Date.valueOf(fechaFormateada);
    }

    public static String toUtilDate(Date sqlDate) {
        if (sqlDate == null) {
            return null;
        }
        SimpleDateFormat formato = new SimpleDateFormat("YYYY-MM-DD");
        String fechaFormateada = formato.format(sqlDate);
       
        return fechaFormateada;
    }
}

