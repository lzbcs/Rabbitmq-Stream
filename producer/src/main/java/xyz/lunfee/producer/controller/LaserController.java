package xyz.lunfee.producer.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.lunfee.producer.entity.LaserScan;

/**
 * @author lunfee
 * @create 2021/12/23-22:57
 */
@Slf4j
@RestController
public class LaserController {
    private StreamBridge streamBridge;
    public LaserController(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }
    @GetMapping("/laser")
    public ResponseEntity<LaserScan> lasers(@RequestBody LaserScan laser) {
        log.info("Sending laser {} to topic", laser);
        streamBridge.send("lasers-out-0", laser);
        return ResponseEntity.ok(laser);
    }
}
