package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {
	
	//得到当前时间
    public static String getCurrentTime() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        //Date转String
        return sdf.format(date);
    }
    
  //得到当前时间
    public static String getCurrentTime2() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //Date转String
        return sdf.format(date);
    }
    
  //得到当前时间
    public static String getCurrentTimeBaseDay() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        //Date转String
        return sdf.format(date);
    }
    
}
