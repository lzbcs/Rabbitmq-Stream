package xyz.lunfee.consumer.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * @author lunfee
 * @create 2021/12/26-17:57
 * 从yaml文件中加载配置（所有以 hbase 开头）
 */

@ConfigurationProperties(prefix="hbase")
public class HbaseProperties {
    private Map<String, String> config;

    public Map<String, String> getConfig() {
        return config;
    }

    public void setConfig(Map<String, String> config) {
        this.config = config;
    }
}
