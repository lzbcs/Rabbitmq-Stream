package xyz.lunfee.consumer.component;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import xyz.lunfee.consumer.entity.LaserScan;

import java.util.function.Consumer;

/**
 * @author lunfee
 * @create 2021/12/23-23:09
 */
@Slf4j
@Component
public class LaserConsumer {
    @Bean
    public Consumer<String> onReceive() {
        return (laser) -> {
//            log.info("Received the Laser {} in Consumer", laser);
            LaserScan laserScan = JSON.parseObject(laser, LaserScan.class);
//            System.out.println(laserScan);
            double[] ranges = laserScan.getRanges();

        };
    }
}
