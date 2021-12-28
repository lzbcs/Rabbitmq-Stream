package xyz.lunfee.consumer.component;


import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import xyz.lunfee.consumer.entity.LaserMessage;
import xyz.lunfee.consumer.utils.HbaseClientUtils;
import xyz.lunfee.consumer.utils.TimestampUtils;

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

    @RabbitListener(queues = "laser.topic")
    public void receiveMessage(String laser) {
//        log.info("Receive a new message"+laser);
        LaserMessage laserMessage = JSON.parseObject(laser, LaserMessage.class);
        String ymdhms = TimestampUtils.stampToDate((int) laserMessage.getSecs());
        //由 laserScan的yyyymmddhhMMss --> mmddhhMMss
        String mdhms = ymdhms.substring(4, 14);
        //laserScan的9位纳秒数据 --> 3位毫秒数据
        String ms = laserMessage.getNsecs().substring(0, 3);


        double[] ranges = laserMessage.getRanges();
        double[] intensities = laserMessage.getIntensities();

        //唯一Rowkey由"L"代表LaserScan，由mmddhhMMss + SSS代表唯一时间标识，便于字典查找
        String rowKey = "L" + mdhms + ms;
        String range = JSON.toJSONString(ranges);
        String intensity = JSON.toJSONString(intensities);
        HbaseClientUtils.addData("intensities", rowKey, "range", "range", range, "intensities", intensity);
    }


}
