package xyz.lunfee.consumer.utils;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author lunfee
 * @create 2021/12/28-14:29
 */
@Component
public class TimestampUtils {

    public static String dateToStamp(String s) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String res = "";
        if (!"".equals(s)) {
            try {
                res = String.valueOf(sdf.parse(s).getTime() / 1000);
            } catch (Exception e) {
                System.out.println("传入了null值");
            }
        } else {
            long time = System.currentTimeMillis();
            res = String.valueOf(time / 1000);
        }

        return res;
    }


    /*
     * 将时间戳转换为时间
     */
    public static String stampToDate(int time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String times = format.format(new Date(time * 1000L));
//	    System.out.println("日期格式---->" + times);
        return times;
    }
}
