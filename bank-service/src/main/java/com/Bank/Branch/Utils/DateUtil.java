package com.Bank.Branch.Utils;

import com.Bank.Branch.Security.JwtConstants;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;


public class DateUtil {

    public static Date dateAfter(String timeString){
        String format = timeString.substring(timeString.length()-1);
        if(isValidJwtDateFormat(format)){
             int parsedTime = Integer.parseInt(timeString.substring(0, timeString.indexOf(format)));
             return afterTime(parsedTime, format);
        }
        return null;
    }

    public static boolean isValidJwtDateFormat(String time){
        return time.matches(JwtConstants.JWT_VALID_TIME_FORMAT);
    }

    public static Date afterTime(int time, String format){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        switch (format) {
            case JwtConstants.JWT_TIME_FORMAT_HOUR -> calendar.add(Calendar.HOUR, time);
            case JwtConstants.JWT_TIME_FORMAT_MINUTE -> calendar.add(Calendar.MINUTE, time);
            case JwtConstants.JWT_TIME_FORMAT_SECOND -> calendar.add(Calendar.SECOND, time);
            case JwtConstants.JWT_TIME_FORMAT_MILLI_SECOND -> calendar.add(Calendar.MILLISECOND, time);
        }
        return calendar.getTime();
    }

    public static Integer difference(Date date1, Date date2) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate d1 = LocalDate.parse(sdf.format(date1), DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate d2 = LocalDate.parse(sdf.format(date2), DateTimeFormatter.ISO_LOCAL_DATE);
        Duration diff = Duration.between(d1.atStartOfDay(), d2.atStartOfDay());
        return Math.toIntExact(diff.toDays());
    }
}
