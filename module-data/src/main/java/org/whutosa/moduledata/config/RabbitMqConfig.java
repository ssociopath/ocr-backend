package org.whutosa.moduledata.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author bobo
 * @date 2021/3/28
 * 消息队列相关配置
 */

@Configuration
public class RabbitMqConfig {

    @Bean
    public Queue wholeImageQueue(){
        return new Queue("wholeImage");
    }

    @Bean
    public Queue singleImageQueue(){
        return new Queue("singleImage");
    }

    @Bean
    public Queue outputQueue(){
        return new Queue("output");
    }
}
