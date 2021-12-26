package xyz.lunfee.processor.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Function;

/**
 * @author lunfee
 * @create 2021/12/23-23:04
 */
@Slf4j
@Component
public class ValueProcessor {
    @Bean
    public Function<String, String> convertToUppercase() {
        return (value) -> {
            log.info("Received {}", value);
            String upperCaseValue = value.toUpperCase();
            log.info("Sending {}", upperCaseValue);
            return upperCaseValue;
        };
    }
}
