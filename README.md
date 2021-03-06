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

解决方法
> git清理缓存，pom.xml新加module，idea清理缓存

--- 

- 关于DAO/DTO等

> https://zhuanlan.zhihu.com/p/42288383

---

- 跨域问题

> 前端访问AJAX访问后端接口时报 CROS error

解决方法

> https://blog.csdn.net/tg928600774/article/details/80325040

---

- javascript 执行顺序问题

> (function() {})，即 $(document).ready(function()), 在DOM 加载完毕之后执行。即页面所有的 html 标签（包括图片等）都加载完了，即浏览器已经响应完了，加载完了，全部展现到浏览器界面上了。

![DOM tree](./documentation/images/html-DOMtree.gif)

> DOM在第一次页面加载完毕后，就在内存里了，无论后面怎么通过ajax的方式去局部修改html页面，都只是对内存中的DOM树进行修改，而DOM在第一次页面加载完毕后就已经加载完毕了。
> 所以后面 js文件（动态加载或者 head 中加载）再使用到 $(function() {}) 函数肯定会执行的。
> 一句话总结就是一般顺序执行 $(function(){}) 用于监控控件，


---

- 无法引用 外部 js包

> 死活引用不了，最后发现要在html中js引用需要有顺序，或者放在头部应该也可以

---

- 页面访问速度很慢

> 原因是挂梯子的时候可以加载一些js包,但是一般人不挂梯子，访问的时候控制台会报404
> 最好把所有的依赖全部down下来，然后前后端分离。

- nginx 动静分离

> echarts.js 等静态资源打包占用太多空间，故使用nginx动静分离，也方便之后集群部署

```shell
http {
  map $http_upgrade $connection_upgrade {
      default upgrade;
      '' close;
  }
  server {
    listen 8080;
    server_name localhost;   
    location / {
      proxy_pass http://localhost:8082;
      #websocket 配置
      proxy_http_version 1.1;
      proxy_set_header Upgrade $http_upgrade;
      proxy_set_header Connection $connection_upgrade;
      proxy_set_header Origin "";
    }
    #静态资源拦截配置
    location ~ .*\.(js|gif)$ {
      root /home/lunfee/static/ui-js;
    }
        
  }


}

```