package xyz.lunfee.consumer.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lunfee
 * @create 2021/12/26-18:21
 */
@Component
@Slf4j
public class HbaseClientUtils {

    private final Connection connection;
    public HbaseClientUtils(Connection connection) {
        this.connection = connection;
    }
    /*
    * @Author lunfee
    * @Description 创建只有一个列族的表
    * @Date 20:07 2021/12/26
    * @Param tableName 表名
    * @Param tableName 列族名
    * @return void
    **/
    public void createTable(String tableName, String colFamily) throws IOException {
        TableName table = TableName.valueOf(tableName);
        try (HBaseAdmin admin = (HBaseAdmin) connection.getAdmin()) {
            if (admin.tableExists(table)) {
                log.warn("表[{}]已存在!", tableName);
                return;
            }
            ColumnFamilyDescriptor cfd = ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes(colFamily))
                    // 设置数据版本数量
                    .setMaxVersions(1)
                    // 设置副本数，默认是3
                    .setDFSReplication((short) 2)
                    .build();
            TableDescriptor tableDes = TableDescriptorBuilder.newBuilder(table).setColumnFamily(cfd).build();
            admin.createTable(tableDes);
        }
    }

    /**
     * 列出hbase中所有的表
     */
    public List<String> listTables() throws IOException {
        List<String> tables = new ArrayList<>(8);
        try (HBaseAdmin admin = (HBaseAdmin) connection.getAdmin()) {
            TableName[] tableNames = admin.listTableNames();
            for (TableName tableName : tableNames) {
                tables.add(tableName.getNameAsString());
            }
        }
        return tables;
    }


}
