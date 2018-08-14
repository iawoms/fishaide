package web;

import com.alibaba.fastjson.JSON;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.GlobalEventExecutor;
import model.Message;

import java.util.Random;


/**
 * Created by iawom on 2017/8/31.
 */
public class MessageHandler {
    public static ChannelGroup activeWebChannels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);


    public static void webActived(Channel channel) {
        activeWebChannels.add(channel);
    }

    static AttributeKey<Boolean> authKey = AttributeKey.valueOf("authed");

    public static void sendMsg2Web(Channel channel, Message msg) {
        String json = JSON.toJSONString(msg);
        TextWebSocketFrame frame = new TextWebSocketFrame(json);
        channel.write(frame);
        channel.flush();
    }

    public static void broadcast2Web(Message msg) {
        String json = JSON.toJSONString(msg);
        TextWebSocketFrame frame = new TextWebSocketFrame(json);
        activeWebChannels.writeAndFlush(frame);
    }


    public static void handleWebMessage(Channel channel, TextWebSocketFrame msgFrame) {
        String json = msgFrame.text();


    }

    public static void main(String[] args) {
        int randModel = new Random().nextInt(8888) + 1001;
        System.out.println(randModel);
    }
}
