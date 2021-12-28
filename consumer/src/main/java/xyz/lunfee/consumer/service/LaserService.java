package xyz.lunfee.consumer.service;

import org.apache.hadoop.hbase.client.Result;
import xyz.lunfee.consumer.entity.Laser;

public interface LaserService {
    public Laser messageToLaser(Result result);
}
