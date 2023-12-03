package com.mentalhealth.framework.websocket;


import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

public class GetHttpSessionConfigurator  extends ServerEndpointConfig.Configurator {

    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
      HttpSession httpSession = (HttpSession) request.getHttpSession();
        // 将 httpSession 对象存储到配置对象中
        // HandshakeRequest 握手请求 HandshakeResponse 握手响应
        // ServerEndpointConfig 服务器端点配置 对象
        // getUserProperties 获取用户属性
        // HttpSession.class.getName() 是 HttpSession 对象的字节码名称  作为 Map 键的唯一标识
        sec.getUserProperties().put(HttpSession.class.getName(),httpSession);
    }
}
