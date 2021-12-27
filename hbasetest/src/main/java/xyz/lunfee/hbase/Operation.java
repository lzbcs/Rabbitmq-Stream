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
        AtomicInteger atomicInteger = new AtomicInteger(1);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMddHHssSSS");
        String timeStamp = simpleDateFormat.format(new Date());
        System.out.println(timeStamp);
    }
}
