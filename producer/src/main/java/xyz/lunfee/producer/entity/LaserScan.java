package xyz.lunfee.producer.entity;


import lombok.Data;

/**
 * @author lunfee
 * @create 2021/12/24-16:40
 */
@Data
public class LaserScan {
    double min;
    double max;
    double increase;
    double[] ranges;
}
