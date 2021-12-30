package xyz.lunfee.consumer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author lunfee
 * @create 2021/12/30-10:11
 */
@Data
@AllArgsConstructor
public class LaserRanges {
    double[] ragnes;
    double[] intensities;
}
