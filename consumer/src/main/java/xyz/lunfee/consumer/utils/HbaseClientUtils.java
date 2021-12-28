package xyz.lunfee.consumer.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
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


    private static Connection connection;

    private static HBaseAdmin admin;


    public HbaseClientUtils(Connection connection, HBaseAdmin admin) {
        this.admin = admin;
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

    public static void createTable(String tableName, String colFamily) throws IOException {

        if (isTableExists(tableName)) {
            log.warn("Table [{}] exists", tableName);
            return;
        }
        TableName table = TableName.valueOf(tableName);

        ColumnFamilyDescriptor cfd = ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes(colFamily))
                // 设置数据版本数量
                .setMaxVersions(1)
                // 设置副本数，默认是3
                .setDFSReplication((short) 2)
                .build();
        TableDescriptor tableDes = TableDescriptorBuilder.newBuilder(table).setColumnFamily(cfd).build();
        admin.createTable(tableDes);


    }

    /*
     * @Author lunfee
     * @Description 创建多个cf的表
     * @Date 10:55 2021/12/27
     * @Param [java.lang.String, java.lang.String...]
     * @return void
     **/
    public static void createTable(String tableName, String... columnFamilies) {

        try {
            //判断表是否存在
            if (isTableExists(tableName)) {
                log.warn("Table [{}] exists", tableName);
                return;
            }
            TableName table = TableName.valueOf(tableName);

            List<ColumnFamilyDescriptor> families = new ArrayList<>();
            for (String columnFamily : columnFamilies) {
                families.add(ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes(columnFamily))
                        // 设置数据版本数量
                        .setMaxVersions(1)
                        // 设置副本数，默认是3
                        .setDFSReplication((short) 2)
                        .build()
                );
            }

            TableDescriptor tableDes = TableDescriptorBuilder.newBuilder(table).setColumnFamilies(families).build();
            admin.createTable(tableDes);
            log.info("Table [{}] created", tableName);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    /*
     * @Author lunfee
     * @Description 列出所有表
     * @Date 10:28 2021/12/27
     * @Param []
     * @return java.util.List<java.lang.String>
     **/
    public static List<String> listTables() throws IOException {
        List<String> tables = new ArrayList<>(8);

        TableName[] tableNames = admin.listTableNames();
        for (TableName tableName : tableNames) {
            tables.add(tableName.getNameAsString());
        }
        close();
        return tables;
    }

    /*
     * @Author lunfee
     * @Description 表插入数据
     * @Date 11:23 2021/12/27
     * @Param [java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String]
     * @return void
     **/
    public synchronized static void addData(String tableName, String rowKey, String columnFamily, String column, String value) {
        if (!isTableExists(tableName)) {
            log.warn("Table [{}] does not exist!", tableName);
            return;
        }
        try {
            Put put = new Put(Bytes.toBytes(rowKey));
            put.addColumn(Bytes.toBytes(columnFamily), Bytes.toBytes(column), Bytes.toBytes(value));
            Table table = connection.getTable(TableName.valueOf(tableName));
            table.put(put);
            table.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /*
     * @Author lunfee
     * @Description 判断表是否存在
     * @Date 10:31 2021/12/27
     * @Param [java.lang.String]
     * @return boolean
     **/
    public static boolean isTableExists(String tbn) {
        TableName table = TableName.valueOf(tbn);
        try {
            return admin.tableExists(table);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /*
     * @Author lunfee
     * @Description 关闭资源连接
     * @Date 10:33 2021/12/27
     * @Param []
     * @return void
     **/
    public static void close() {
        if (admin != null) {
            try {
                admin.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
