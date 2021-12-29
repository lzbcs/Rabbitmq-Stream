package xyz.lunfee.consumer.dao;

import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.stereotype.Repository;
import xyz.lunfee.consumer.utils.HbaseClientUtils;

import java.io.IOException;


@Slf4j
@Repository
public class HbaseDAO {
    private static Connection connection;
    public HbaseDAO(Connection connection) {
        HbaseDAO.connection = connection;
    }

    /*
    * @Author lunfee
    * @Description 获取一个行，返回Result对象
    * @Date 10:27 2021/12/29
    * @Param [java.lang.String, java.lang.String]
    * @return org.apache.hadoop.hbase.client.Result
    **/
    public static Result getRow(String tableName, String rowkey){
        try(Table table = connection.getTable(TableName.valueOf(tableName))){
            Get get = new Get(Bytes.toBytes(rowkey));
            return table.get(get);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    /*
     * @Author lunfee
     * @Description 表插入数据
     * @Date 11:23 2021/12/27
     * @Param [java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String]
     * @return void
     **/
    public synchronized static void addData(String tableName, String rowKey, String columnFamily, String column, String value) {
        if (!HbaseClientUtils.isTableExists(tableName)) {
            log.warn("Table [{}] does not exist!", tableName);
            return;
        }
        try {
            Put put = new Put(Bytes.toBytes(rowKey));
            put.addColumn(Bytes.toBytes(columnFamily), Bytes.toBytes(column), Bytes.toBytes(value));
            Table table = connection.getTable(TableName.valueOf(tableName));
            table.put(put);
            table.close();
            log.info("Data with Rowkey [{}] was added to database", rowKey);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
