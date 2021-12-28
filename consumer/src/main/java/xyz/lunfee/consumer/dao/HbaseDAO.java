package xyz.lunfee.consumer.dao;

import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class HbaseDAO {
    private static Connection connection;

    public HbaseDAO(Connection connection) {
        HbaseDAO.connection = connection;
    }

    public static Result getRow(String tableName, String rowkey){
        try(Table table = connection.getTable(TableName.valueOf(tableName))){
            Get get = new Get(Bytes.toBytes(rowkey));
            return table.get(get);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
