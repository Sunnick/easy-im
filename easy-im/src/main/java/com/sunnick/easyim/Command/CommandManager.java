package com.sunnick.easyim.Command;

import com.sunnick.easyim.protocol.Command;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Sunnick on 2019/1/18/018.
 *
 * 命令管理器
 */
public class CommandManager {

    private static Logger logger = LoggerFactory.getLogger(CommandManager.class);

    /**
     * 单例模式
     */
    private static CommandManager instance = new CommandManager();

    private static Map<String,ConsoleCommand> commandMap = new ConcurrentHashMap<String, ConsoleCommand>();

    static {
        commandMap.putIfAbsent("sendToUser",new P2PCommand());
        commandMap.putIfAbsent("broadcast",new BroadcastCommand());
        commandMap.putIfAbsent("createGroup",new CreateGroupCommand());

    }

    public static CommandManager getInstance(){
        return instance;
    }

    private CommandManager(){  }


    /**
     * 命令执行：
     * 单聊 sendToUser::userId::msg
     * 群聊 sendToGroup::groupId
     * 发起群聊 createGroup::userId1,userId2,userId3...
     * 退出群聊 quitGroup::groupId
     * 加入群聊 joinGroup::groupId
     * 查询所有在线用户 getAllUsers
     * 查询群聊中在线用户 getGroupUsers::groupId
     * 广播 broadcast::msg
     */
    public static void exec(Channel channel,String msg){
        String[] strs = msg.split("::");
        ConsoleCommand command = commandMap.get(strs[0]);
        if(command != null){
            command.exec(channel,msg);
        }else{
            logger.info("命令输入有误，请使用 command::content 的格式！");
        }
    }
}
