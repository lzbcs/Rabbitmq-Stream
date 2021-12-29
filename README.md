# Rabbitmq-Stream
该项目的目的是将从移动机器人上获取雷达数据传送到rabbitmq，SpringBoot程序用于获取消息展示并存储到Hbase数据库
## 开发中遇到的问题


- Hbase连接
> 连接Hbase时要获取连接，为了避免过度使用连接资源，在项目中将 connection 和 admin 设置为单例模式；又因为考虑到要对资源进行关闭，就在每个方法后面进行了资源释放，导致判断表存在后就无法获取连接，或者每次插入一条数据后就自动断开。


解决方法
> 暂时是将所有资源关闭语句全部删除，但是一直到程序结束都没有将连接释放，会不会对后台程序造成影响？待更优雅的连接方式


---

- 与Docker Hbase 中的Zookeeper通信
> 以下配置无法连接到Zookeeper, 
```yaml
hbase:
  config:
    hbase:
      master: 101.43.56.164:16010
      zookeeper:
        property:
          clientPort: 2181
        quorum: 101.43.56.164
```



解决方法
> zookeeper.quorum 必须配置servername 必须要在hosts文件中设置其与远程服务器之间的映射

--- 

- git问题

> 更新gitignore时远程仓库不能同步，git下来的module不能识别，自己的包报红


> git清理缓存，pom.xml新加module，idea清理缓存

--- 

- 关于DAO/DTO等

> https://zhuanlan.zhihu.com/p/42288383

---

- 