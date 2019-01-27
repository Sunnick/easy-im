# easy-im：一款基于netty的即时通讯系统
## 介绍
easy-im是面向开发者的一款轻量级、开箱即用的即时通讯系统，帮助开发者快速搭建消息推送等功能。
基于easy-im，你可以快速实现以下功能：

`` + 聊天软件 ``

`` + IoT消息推送 ``

## 基本用法
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
##### 后边打算将服务端做成可无限扩展的架构，使之满足高并发的需求。具体分为以下几个步骤：

``` 增加路由层： ``` 所有发送的消息由路由层转发给响应的服务端，服务端再由长连接转发至客户端，以此解决发送端与接收端不在同一台服务器上，无法通讯的问题

``` 增加存储介质： ``` 将所有群组关系、客户端与服务器的对应关系、用户信息等存储起来，可以用redis或者MySQL，也可存储历史消息

``` 增加服务治理： ``` 服务端如果要无限扩展，必须通过服务注册、服务发现的机制，可以通过zookeeper，或者eureka实现

##### 有兴趣共同参与的同学，请加我微信 enM2MTA1MzI2 (请用base64解码),一起完善，共同进步。

---

注：本项目的部分设计参考了闪电侠的掘金小册《Netty入门与实战：仿写微信IM即时通讯系统》，如有侵权，请联系我：zhangshun9201@163.com

