package com.mentalhealth.framework.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mentalhealth.project.system.message_record.domain.Message;
import com.mentalhealth.utils.MessageUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;


/**
 * websocket 消息处理
 *
 * @author zhengyuzhu
 */
// 交给springboot处理      //configurator = GetHttpSessionConfigurator.class 声明字节码对象
@Component
@ServerEndpoint(value = "/chat" , configurator = GetHttpSessionConfigurator.class)   //资源路径
public class ChatEndpoint {

    // 用来存储每一个客户端对象对应的 ChatEndpoint（聊天端点） 对象
    private static Map<String, ChatEndpoint> onlineUsers = new ConcurrentHashMap<>();  // Map的子实现类 并发编程

    // 声明 session 对象,通过该对象可以发送消息给指定的用户
    private Session session;

    // 声明一个 HttpSession 对象， 我们之前在HttpSession对象中存储了用户名
    private HttpSession httpSession;

    /**
     * 连接建立成功调用的方法  连接建立时被调用
     */
    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
        // 将局部的 session 对象赋值给成员 session对象
        this.session = session;
        // 获取 httpSession 对象
        HttpSession httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        this.httpSession = httpSession;
        // 从 httpSession 对象中获取用户名
        String userName = (String) httpSession.getAttribute("userName");
        System.out.println("EndpointConfig------userName  :"+userName);
        // 将当前对象存储到容器中
        onlineUsers.put(userName, this);

        // 将当前在线用户的用户名推送给所有的客户端
        // 1. 获取消息
        String message = MessageUtils.getMessage(true, null, getNames());
        // 2. 调用方法进行系统消息推送
        broadcastAllUsers(message);
    }

    // 定义一个方法推送消息
    private void broadcastAllUsers(String message) {
        try {
            //要将该消息推送给所有的客户端
            Set<String> userNames = onlineUsers.keySet();
            for (String userName : userNames) {
                // 根据用户名 获取 chatEndpoint 对象
                ChatEndpoint chatEndpoint = onlineUsers.get(userName);
                chatEndpoint.session.getBasicRemote().sendText(message);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private Set<String> getNames() {
        return onlineUsers.keySet();
    }


    /**
     * 服务器接收到客户端消息时调用的方法  接收到客户端发送到数据时被调用
     */
    @OnMessage
    public void onMessage(String message, Session session) {
      try {
          // 将message 转换成 message 对象 使用 JSON 里面的 ObjectMapper
          ObjectMapper mapper = new ObjectMapper();
          Message mess = mapper.readValue(message, Message.class);
          // 获取 将数据发送给的用户的用户名称
          String toName = mess.getToName();
          System.out.println("          // 获取 将数据发送给的用户的用户名称toName:"+toName);
          // 获取用户要发送到消息数据
          String sendData = mess.getMessage();
          // 获取当前登录的用户
          String username = (String) httpSession.getAttribute("userName");
          System.out.println("// 获取当前登录的用户username:"+username);
          // 获取推送给指定用户的消息格式的数据
          String resultMessage = MessageUtils.getMessage(false, username, sendData);
          // 发送数据      // getBasicRemote 获得基本信息
          onlineUsers.get(toName).session.getBasicRemote().sendText(resultMessage);
      } catch (Exception e) {
          e.printStackTrace();
      }



    }

    /**
     * 连接关闭时处理  连接关闭时被调用
     */
    @OnClose
    public void onClose() {
        String username = (String) httpSession.getAttribute("userName");
        // 从容器中删除指定的用户
        onlineUsers.remove(username);
    }

    /**
     * 抛出异常时处理
     */
    @OnError
    public void onError(Session session, Throwable exception) throws Exception {

    }

}
