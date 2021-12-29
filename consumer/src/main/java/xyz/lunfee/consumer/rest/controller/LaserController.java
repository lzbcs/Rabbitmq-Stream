package xyz.lunfee.consumer.rest.controller;

import io.swagger.annotations.Api;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.hadoop.hbase.client.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.lunfee.consumer.dao.HbaseDAO;
import xyz.lunfee.consumer.entity.Laser;
import xyz.lunfee.consumer.service.LaserService;
import xyz.lunfee.consumer.utils.HbaseClientUtils;

import java.util.List;




/**
 * @author lunfee
 * @create 2021/12/25-17:12
 */
@RestController("/api")

@RequiredArgsConstructor
@Api(tags = "HBase Api")
public class LaserController {
    HbaseDAO hbaseDAO;
    HbaseClientUtils hbaseClientUtils;
    LaserService laserService;

    @Autowired
    public LaserController(HbaseDAO hbaseDAO, HbaseClientUtils hbaseClientUtils, LaserService laserService) {
        this.hbaseDAO = hbaseDAO;
        this.hbaseClientUtils = hbaseClientUtils;
        this.laserService = laserService;
    }


    @GetMapping("/list")
    @ApiOperation("list all the tables in the dataset")
    public List<String> list() throws Exception{
        return HbaseClientUtils.listTables();
    }

    @PostMapping("/delete/{tableName}")
    @ApiOperation("delete table")
    public ResponseEntity<String> removeTableByName(@PathVariable String tableName){

        return HbaseClientUtils.removeTable(tableName) ? ResponseEntity.ok("Table " +tableName+ " deleted") :
                ResponseEntity.ok("Table does not exist or some error happens");
    }

    @PostMapping("/create/{tableName}/{cfName}")
    @ApiOperation("create a table")
    public ResponseEntity<String> create(@PathVariable String tableName, @PathVariable String cfName) throws Exception{
        HbaseClientUtils.createTable(tableName,cfName);
        return ResponseEntity.ok(tableName + "Table Created");
    }

    @PostMapping("/exist/{tableName}")
    @ApiOperation("Does table exist?")
    public ResponseEntity<Boolean> existsTb(@PathVariable String tableName){
        return HbaseClientUtils.isTableExists(tableName) ? ResponseEntity.ok(true) : ResponseEntity.ok(false);
    }

    @GetMapping("/queryByRowkey/{tableName}/{rowkey}")
    @ApiOperation("query with rowkey")
    public ResponseEntity<Laser> query(@PathVariable String tableName, @PathVariable String rowkey){
        Result result = HbaseDAO.getRow(tableName, rowkey);
        Laser laser = laserService.messageToLaser(result);
        return ResponseEntity.ok(laser);
    }
}
