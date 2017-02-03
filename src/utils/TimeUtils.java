package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {
	
	//�õ���ǰʱ��
    public static String getCurrentTime() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        //DateתString
        return sdf.format(date);
    }
    
  //�õ���ǰʱ��
    public static String getCurrentTime2() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //DateתString
        return sdf.format(date);
    }
    
  //�õ���ǰʱ��
    public static String getCurrentTimeBaseDay() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        //DateתString
        return sdf.format(date);
    }
    
}
