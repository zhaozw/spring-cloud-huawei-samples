
# 项目说明

这个项目提供了 Spring Cloud Huawei集成seata 的简单例子，例子包括：

* provider-order
使用 Spring Cloud 开发一个 REST 接口，并在此接口里面通过RestTemplate 调用 provider-account和 provider-storage的接口。

* provider-account
使用 Spring Cloud 开发一个 REST 接口。

* provider-storage
使用 Spring Cloud 开发一个 REST 接口。

* consumer
使用 Spring Cloud 开发一个 REST 接口， 接口实现通过 RestTemplate 调用 provider-order 的接口。 

* gateway
使用 Spring Cloud Gateway 开发一个网关， 网关将所有请求转发到 consumer。 

## 使用

* 前提条件
[准备CSE运行环境](../README_CN.md)
* 下载[seata集成cse后的代码](https://github.com/zhaozw/seata/)
* 执行mvn clean install -Dmaven.test.skip=true
* 安装mysql  例子中的用户名和密码分别是root 123456，如果不同需要做相应修改，seata-server和三个provider都有数据库配置
* 导入etc下面的sql文件到数据库
* 在cse上创建创建配置，内容在etc/seata.properties（如果seata-server和客户端不是一个应用的话，需要分别配置或者通过自定义配置确保都能获取到配置）
* seata-server的application.yml可以参考ect/application.yml 启动seata-server，如果是通过idea源码启动，springboot方式启动io.seata.server。ServerApplication  也可以通过命令行启动，命令行启动需要先执行mvn clean install -Prelease-seata -Dmaven.test.skip=true    然后去源码根目录\distribution\target\seata-server-1.5.0-SNAPSHOT\seata\bin 下启动seata-server.bat

* 编译

        mvn clean package

* 启动 provider-order

  进入目录 ${Project}/provider-order/target/
  
        java -jar seata-provider-order-1.0-SNAPSHOT.jar

* 启动 provider-account

  进入目录 ${Project}/provider-account/target/
  
        java -jar seata-provider-account-1.0-SNAPSHOT.jar

* 启动 provider-storage

  进入目录 ${Project}/provider-storage/target/
  
        java -jar seata-provider-storage-1.0-SNAPSHOT.jar

* 启动 consumer

  进入目录 ${Project}/consumer/target/

        java -jar seata-consumer-1.0-SNAPSHOT.jar

* 启动 gateway

  进入目录 ${Project}/gateway/target/

        java -jar seata-gateway-1.0-SNAPSHOT.jar

* 测试

启动5个微服务后， 然后通过界面访问： 
http://localhost:9090/createSuccessOrder 这个可以看到数据库更新了。

http://localhost:9090/createRollbackOrder 这个可以看到数据库更新之后又被回滚了。

测试中可能遇到的问题
启动provider的时候报错，类似找不到servicecomb配置什么的，这个可能是由于seata官方经常会上次最新的snapshot版本到仓库，类似seata-all-1.5.0-SNAPSHOT-xxxx日期.jar，然后就会莫名其妙的引用带日期的版本，包括seata-server执行mvn clean install -Dmaven.test.skip=true的时候也经常会出现引用错误的版本问题，不知道如何禁止或避免，我的方法是如果有问题就，针对seata-config-servicecomb seata-config-core seata-discovery-servicecomb seata-all seata-spring-autoconfigure-core 按顺序执行一遍就好了

测试过程中遇到的问题，之前用Feign的demo可以通过sleep模拟超时如何报错，但是在这个例子测试的时候，不会超时，会一直等待，不知道哪里可以设置，所以只通过直接抛出异常来触发回滚

        if (!success){
            throw new RuntimeException("模拟异常");
        }

现在测试结果只能通过查看数据库来验证，这个也是不方便的地方