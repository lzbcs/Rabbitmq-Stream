package xyz.lunfee.consumer.controller;

import io.swagger.annotations.Api;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.lunfee.consumer.utils.HbaseClientUtils;

import java.util.List;


/**
 * @author lunfee
 * @create 2021/12/25-17:12
 */
@RestController

@RequiredArgsConstructor
@Api(tags = "HBase Api")
public class LaserController {

    private final HbaseClientUtils hbaseClientUtils;

    @GetMapping("list")
    @ApiOperation("列出hbase中所有的表")
    public List<String> list() throws Exception{
        List<String> list = hbaseClientUtils.listTables();
        return list;
    }


    @PostMapping("/create/{tableName}/{CFName}")
    @ApiOperation("创建表")
    public ResponseEntity create(@RequestParam("tableName") String tn, @RequestParam("CFName") String cfn) throws Exception{
        hbaseClientUtils.createTable(tn,cfn);
        return ResponseEntity.ok(tn + "Table Created");
    }
}
