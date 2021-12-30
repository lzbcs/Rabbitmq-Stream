package xyz.lunfee.consumer.service;

import org.apache.hadoop.hbase.client.Result;
import xyz.lunfee.consumer.entity.Laser;
import xyz.lunfee.consumer.entity.LaserRanges;

import java.util.List;
import java.util.Map;

public interface LaserService {
    public LaserRanges messageToLaserRanges(Result result);
    public List<List<Double>> laserrangeToPointCloud(LaserRanges laserRanges);
}
