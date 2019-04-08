package cdictv.diertao.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtil {
    public static String showData(){
        String data=null;
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy年MM月dd日");
        Calendar calendar=Calendar.getInstance();
        data=simpleDateFormat.format(calendar.getTime());
        return data;
    }
    public static String showData2(){
        String data=null;
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd");
        Calendar calendar=Calendar.getInstance();
        data=simpleDateFormat.format(calendar.getTime());
        return data;
    }

    public static String showWeek(){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("EEEE");
        Calendar calendar=Calendar.getInstance();
        String data=simpleDateFormat.format(calendar.getTime());
        return data;
    }
public static String showWeek(int d){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("EEEE");
        Calendar calendar=Calendar.getInstance();
        calendar.set(Calendar.YEAR,Calendar.MONTH,Calendar.DAY_OF_MONTH+d);
        String data=simpleDateFormat.format(calendar.getTime());
        return data;
    }
}
