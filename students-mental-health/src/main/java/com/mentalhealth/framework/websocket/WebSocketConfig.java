package com.mentalhealth.framework.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * websocket 配置
 * 
 * @author zhengyuzhu
 */
@Configuration   //配置类
public class WebSocketConfig
{
    //注入 ServerEndpointExporter bean对象，自动注册使用了@ServerEndpoint 注解的bean
    @Bean
    public ServerEndpointExporter serverEndpointExporter()
    {
        return new ServerEndpointExporter();
    }


}
