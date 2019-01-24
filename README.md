# easy-im：一款基于netty的即时通讯系统
## 介绍
easy-im是面向开发者的一款轻量级、开箱即用的即时通讯系统，帮助开发者快速搭建消息推送等功能。
基于easy-im，你可以快速实现以下功能：

`` + 聊天软件 ``

`` + IoT消息推送 ``

## 基本用法
项目分为easy-im-client、easy-im-server、easy-im-common三个模块。

``` 服务端： ``` 执行mvn package后生成easy-im-server.tar.gz，解压后至./lib目录执行命令：java -jar easy-im-server-1.0-SNAPSHOT.jar,即可启动服务端。


``` 客户端： ``` 执行mvn package后生成easy-im-client.tar.gz，解压后至./lib目录执行命令：java -jar easy-im-client-1.0-SNAPSHOT.jar userid username。其中userid为用户id，username为用户名，userid要保持唯一性。

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

注：本项目的部分设计参考了闪电侠的掘金小册《Netty入门与实战：仿写微信IM即时通讯系统》，如有侵权，请联系我：zhangshun9201@163.com

