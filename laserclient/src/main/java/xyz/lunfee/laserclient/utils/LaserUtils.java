package xyz.lunfee.laserclient.utils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author lunfee
 * @create 2021/12/30-17:54
 */
public class LaserUtils {
    /*
    * @Author lunfee
    * @Description //方法用于将laser double数组 返回为 前端ui方便接收的类型
    * @Date 17:57 2021/12/30
    * @Param [double[]]
    * @return java.util.List<java.util.List<java.lang.Double>>
    **/
    public static List<List<Double>>  rangeToPointCloud (double[] range){
        List<List<Double>> pcList = new LinkedList<>();
        //雷达扫描角度为270度 分辨率0.25，共1081个点
        double angle = -45; //极
        double x = 0;
        double y = 0;

        for (double point : range) {
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
