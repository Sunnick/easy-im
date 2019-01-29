# easy-im：一款基于netty的即时通讯系统
## 介绍
easy-im是面向开发者的一款轻量级、开箱即用的即时通讯系统，帮助开发者快速搭建消息推送等功能。
基于easy-im，你可以快速实现以下功能：

`` + 聊天软件 ``

`` + IoT消息推送 ``

## 基本用法--单机版（v1分支）
项目分为easy-im-client、easy-im-server、easy-im-common三个模块。

``` 服务端： ``` 执行mvn package后生成easy-im-server.tar.gz，解压后至./lib目录执行命令：
java -jar -Dport=8888  easy-im-server-1.0-SNAPSHOT.jar,即可启动服务端,其中port是服务端口。


``` 客户端： ``` 执行mvn package后生成easy-im-client.tar.gz，解压后至./lib目录执行命令：
java -jar -Duserid=110 -Dusername=zhangsan -Dhost=127.0.0.1 -Dport=8888 easy-im-client-1.0-SNAPSHOT.jar。
其中userid为用户id，username为用户名，host为服务端ip，port为服务端端口，其中userid要保持唯一性。

``` 用法： ``` 客户端启动后，在命令行输入命令，命令格式为 command::content ，命令以英文双冒号为分隔符，现已支持如下命令：

`` + 单聊 sendToUser::userId::msg  ``

`` + 群聊 sendToGroup::groupId::msg  ``

`` + 发起群聊 createGroup::userId1,userId2,userId3...  ``

`` + 广播 broadcast::msg  ``

后续计划加入更多命令，如：

`` + 退出群聊 quitGroup::groupId  ``

`` + 加入群聊 joinGroup::groupId  ``

`` + 查询所有在线用户 getAllUsers  ``

`` + 查询群聊中在线用户 getGroupUsers::groupId  ``

---

## 计划中-集群版（v2分支）

#### 打算将服务端做成可无限扩展的架构，使之满足高并发的需求。

#### 架构图如下：

![](https://user-gold-cdn.xitu.io/2019/1/29/168998972ea46343?w=627&h=480&f=png&s=17428)

#### 交互步骤：
##### 1. 启动server，将server注册到注册中心
##### 2. 启动路由层（route），到注册中心获取可用的server列表
##### 3. 启动client，到route获取一个server信息（route层实现选择策略，默认轮循）
##### 4. client与server建立长连接，并将server与client的对应关系存储至Redis或MySQL

#### 注：
##### 1. route层采用http对外提供无状态的服务，可以采用nginx无限扩展
##### 2. server注册至注册中心，也可以无限扩展，用以实现百万连接
##### 3. client之间互聊时，需发送消息至route，route到redis中查找对应的server，再将消息通过server转发至对方的client，以实现两个client不在同一台server时互聊
---

**目前集群版（v2分支）正在计划中，欢迎有兴趣的同学共同参与，共同进步。**

---


## 联系作者
- [zhangshun9201@163.com](mailto:zhangshun9201@163.com)
- 个人微信号

![](https://user-gold-cdn.xitu.io/2019/1/29/168999f3dcc33273?w=430&h=430&f=jpeg&s=40811)
- 微信公众号

![](https://user-gold-cdn.xitu.io/2019/1/27/1688fbaaa4a0b0f3?w=254&h=241&f=png&s=43837)



