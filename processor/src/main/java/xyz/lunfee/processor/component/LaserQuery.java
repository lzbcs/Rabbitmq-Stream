package xyz.lunfee.processor.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import java.util.function.Consumer;

/**
 * @author lunfee
 * @create 2021/12/23-23:04
 */
@Slf4j
@Component
public class LaserQuery {
    @Bean
    public Consumer<String> query() {
        return (value) -> {
            log.info("Received {}", value);
        };
    }
}
