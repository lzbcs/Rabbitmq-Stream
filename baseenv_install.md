# 基础软件的安装
记录下经常重复性安装的软件，比如JVM, Maven, Anaconda, pycharm, idea, cuda, python 版本管理, 

--- 

##nginx

源码安装
> https://blog.csdn.net/qq_23832313/article/details/83578836

连前端都看得懂的《Nginx 入门指南》
> https://juejin.cn/post/6844904129987526663

- 启动与关闭

  ./nginx  
  ./nginx -s stop 
  ./nginx -s quit  
  ./nginx -s reload  
  ps aux|grep nginx

- 原理

>

##jdk

1. 下载jdk压缩文件

2. 解压源文件
```shell
tar -zxvf jdk-11.0.12_linux-x64_bin.tar.gz
```
3. 配置环境变量
```shell
sudo vim /etc/profile.d/my_env.sh
```

```shell
export JAVA_HOME=$解压路径
export PATH=$PATH:$JAVA_HOME/bin
export CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
export JRE_HOME=$JAVA_HOME/jre
```
4. 刷新环境变量
```shell
source /etc/profile
```
5. 验证
```shell
java -version
```


##Docker

- Uninstall(卸载所有)
```shell
 sudo apt-get purge docker-ce docker-ce-cli containerd.io
# 删除images, containers, volumes, or customized configuration files
 sudo rm -rf /var/lib/docker
 sudo rm -rf /var/lib/containerd
```
- Install

###Set up the repository
1. Update the apt package index and install packages to allow apt to use a repository over HTTPS:
```shell
 sudo apt-get update
 sudo apt-get install \
    ca-certificates \
    curl \
    gnupg \
    lsb-release
```

2. Add Docker’s official GPG key:
```shell
 curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --dearmor -o /usr/share/keyrings/docker-archive-keyring.gpg
```

3. Use the following command to set up the stable repository. To add the nightly or test repository, add the word nightly or test (or both) after the word stable in the commands below. Learn about nightly and test channels.

```shell
 echo \
  "deb [arch=$(dpkg --print-architecture) signed-by=/usr/share/keyrings/docker-archive-keyring.gpg] https://download.docker.com/linux/ubuntu \
  $(lsb_release -cs) stable" | sudo tee /etc/apt/sources.list.d/docker.list > /dev/null
```

###Install Docker Engine

1. Update the apt package index, and install the latest version of Docker Engine and containerd, or go to the next step to install a specific version:
```shell
 sudo apt-get update
 sudo apt-get install docker-ce docker-ce-cli containerd.io
```

2. Verify that Docker Engine is installed correctly by running the hello-world image.

```shell
sudo docker run hello-world
```


##Docker-compose

1. 官方
```shell
sudo curl -L "https://github.com/docker/compose/releases/download/1.29.2/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
```
国内
```shell
curl -L https://get.daocloud.io/docker/compose/releases/download/1.29.2/docker-compose-`uname -s`-`uname -m` > /usr/local/bin/docker-compose
```
2. 加权限

```shell
sudo chmod +x /usr/local/bin/docker-compose
```

3. 确认安装
```shell
docker-compose --version
```
