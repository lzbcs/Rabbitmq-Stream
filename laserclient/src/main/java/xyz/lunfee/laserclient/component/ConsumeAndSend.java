package xyz.lunfee.laserclient.component;

import com.alibaba.fastjson.JSON;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import xyz.lunfee.laserclient.entity.LaserMessage;
import xyz.lunfee.laserclient.entity.LaserRanges;
import xyz.lunfee.laserclient.utils.LaserUtils;

import java.util.List;

/**
 * @author lunfee
 * @create 2021/12/30-17:17
 * 动态获取rabbitmq 数据并传到websocket的laser topic
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class ConsumeAndSend {

    private final SimpMessagingTemplate simpMessagingTemplate;

    @RabbitListener(queues = "laser.websocket")
    public void receiveMessage(String laser) {

        log.info("Receive a new message");
        LaserMessage laserMessage = JSON.parseObject(laser, LaserMessage.class);

        double[] ranges = laserMessage.getRanges();
        double[] intensities = laserMessage.getIntensities();
        List<List<Double>> lists = LaserUtils.rangeToPointCloud(ranges);
        log.info("[S] Sent a message");
        simpMessagingTemplate.convertAndSend("/topic/laserranges", lists);


    }
}
