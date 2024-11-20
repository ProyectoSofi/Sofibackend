package co.edu.javeriana.seshat.sofiplus.Kernel;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateFromFront {
    public static Date format(String date) throws ParseException {
        return new Date(new SimpleDateFormat("dd/MM/yyyy").parse(date).getTime());
    }

    public static java.util.Date formatUtilDate(String date) throws ParseException {
        return new Date(new SimpleDateFormat("dd/MM/yyyy").parse(date).getTime());
    }
}
