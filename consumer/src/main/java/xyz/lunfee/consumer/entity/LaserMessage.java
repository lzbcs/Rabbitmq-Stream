package xyz.lunfee.consumer.entity;

import lombok.Data;

/**
 * @author lunfee
 * @create 2021/12/25-16:14
 */
@Data
public class LaserMessage {
    long secs;
    String nsecs;
    double[] intensities;
    double[] ranges;
}
