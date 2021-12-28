package xyz.lunfee.consumer.service.impl;

import com.alibaba.fastjson.JSON;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.stereotype.Service;
import xyz.lunfee.consumer.entity.Laser;
import xyz.lunfee.consumer.service.LaserService;

@Service
public class LaserServiceImpl implements LaserService {
    @Override
    public Laser messageToLaser(Result result) {
        Laser laser = new Laser();
        String laserrange = Bytes.toString(result.getValue(Bytes.toBytes("laser"), Bytes.toBytes("range")));

        String laserintensity = Bytes.toString(result.getValue(Bytes.toBytes("laser"), Bytes.toBytes("intensity")));
//        laser.setRanges();
//        laser.setIntensities();
        return laser;
    }
}
