package xyz.lunfee.consumer.service.impl;

import com.alibaba.fastjson.JSON;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.stereotype.Service;
import xyz.lunfee.consumer.entity.Laser;
import xyz.lunfee.consumer.entity.LaserRanges;
import xyz.lunfee.consumer.service.LaserService;

import java.util.*;

@Service
public class LaserServiceImpl implements LaserService {
    @Override
    public LaserRanges messageToLaserRanges(Result result) {
        double[] laserrange = JSON.parseObject(result.getValue(Bytes.toBytes("laser"), Bytes.toBytes("range")), double[].class);
        double[] laserintensity = JSON.parseObject(result.getValue(Bytes.toBytes("laser"), Bytes.toBytes("intensity")), double[].class);
        LaserRanges laserRanges = new LaserRanges(laserrange, laserintensity);
        return laserRanges;
    }

    @Override
    public List<List<Double>> laserrangeToPointCloud(LaserRanges laserRanges) {
        List<List<Double>> pcList = new LinkedList<>();
        //雷达扫描角度为270度 分辨率0.25，共1081个点
        double angle = -45; //极
        double x = 0;
        double y = 0;

        for (double point : laserRanges.getRagnes()) {
            if(point >= 30){}else
                if (angle < 0) {
                x = point * Math.cos(Math.toRadians(-angle));
                y = - point * Math.sin(Math.toRadians(-angle));
            } else {
                x = point * Math.cos(Math.toRadians(angle));
                y = point * Math.sin(Math.toRadians(angle));

            }
            List<Double> coordinate = new ArrayList<>();
            coordinate.add(x);
            coordinate.add(y);
            pcList.add(coordinate);
            angle += 0.25;
        }

        return pcList;
    }
}
