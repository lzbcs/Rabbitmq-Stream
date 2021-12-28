package xyz.lunfee.consumer.component;


import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import xyz.lunfee.consumer.entity.LaserScan;
import xyz.lunfee.consumer.utils.HbaseClientUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author lunfee
 * @create 2021/12/27-16:08
 */
@Slf4j
@Component
public class ReciveLaserTopic {


    HbaseClientUtils hbaseClientUtils;

    @RabbitListener(queues ="laser.topic" )
    public void receiveMessage(String laser){
        log.info("Receive a new message"+laser);
        LaserScan laserScan = JSON.parseObject(laser, LaserScan.class);
        double[] ranges = laserScan.getRanges();


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMddHHmmssSSS");
        String timeStamp = simpleDateFormat.format(new Date());
        String rowKey = "L" + timeStamp;
        String value = JSON.toJSONString(ranges);
//        HbaseClientUtils.addData("laser", rowKey, "ranges", "range", value);
        HbaseClientUtils.addData("laser", rowKey, "ranges", "range", value);
    }


}
