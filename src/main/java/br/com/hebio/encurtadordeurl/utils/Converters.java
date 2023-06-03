package br.com.hebio.encurtadordeurl.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Converters {

    //format data
    public static String formatDateCalendar(Calendar date, String format) {
        return new SimpleDateFormat(format).format(date.getTime());
    }

    public static Calendar parseStringToCalendar(String date, String separator) {
        String[] d = date.split(separator);
        return new GregorianCalendar(Integer.parseInt(d[0]), Integer.parseInt(d[1]) - 1, Integer.parseInt(d[2]));
    }
}
