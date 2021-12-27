package xyz.lunfee.consumer.config;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.util.Map;

/**
 * @author lunfee
 * @create 2021/12/26-18:12
 */
@org.springframework.context.annotation.Configuration
@EnableConfigurationProperties(HbaseProperties.class)
public class HbaseConfig {
    private final HbaseProperties prop;


    public HbaseConfig(HbaseProperties properties) {
        this.prop = properties;
    }

    @Bean
    public Configuration configuration() {
        Configuration configuration = HBaseConfiguration.create();
        Map<String, String> config = prop.getConfig();
        config.forEach(configuration::set);
        return configuration;
    }

    @Bean
    public Connection getConnection() throws IOException {
        return ConnectionFactory.createConnection(configuration());
    }

    @Bean
    public HBaseAdmin getAdmin() throws IOException {
        return (HBaseAdmin) getConnection().getAdmin();
    }
}
