package xyz.lunfee.hbase;

import org.apache.hadoop.hbase.client.HTable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lunfee
 * @create 2021/12/26-20:14
 */
public class Operation {
    public static void main(String[] args) {
//        AtomicInteger atomicInteger = new AtomicInteger(1);
//
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYYMMddHHss");
//        String timeStamp = simpleDateFormat.format(new Date());
//        System.out.println(timeStamp);
//        long time = System.currentTimeMillis();
//        System.out.println(stampToDate(1640665390));
        long l = System.nanoTime();
        System.out.println(l);
    }

    public static String dateToStamp(String s){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String res = "";
        if (!"".equals(s)) {
            try {
                res = String.valueOf(sdf.parse(s).getTime()/1000);
            } catch (Exception e) {
                System.out.println("传入了null值");
            }
        }else {
            long time = System.currentTimeMillis();
            res = String.valueOf(time/1000);
        }

        return res;
    }


    /*
     * 将时间戳转换为时间
     */
    public static String stampToDate(int time){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String times = format.format(new Date(time * 1000L));
//	    System.out.println("日期格式---->" + times);
        return times;
    }
}
