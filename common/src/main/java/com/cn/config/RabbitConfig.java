package com.cn.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * rabbit配置类   需要使用的项目引入common项目即可使用
 * @auther
 * @date 2021-08-31-16:41
 */
@Configuration
public class RabbitConfig {

    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.port}")
    private int port;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    /**
     * 连接配置
     */
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host,port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setPublisherConfirms(true);
        return connectionFactory;
    }

    /**
     * 配置RabbitTemplate
     */
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    //必须是prototype类型
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        return template;
    }

    /**
     * 交换机名称
     */
    public static final String EXCHANGE_A = "my-mq-exchange_A";
    public static final String EXCHANGE_B = "my-mq-exchange_B";
    public static final String EXCHANGE_C = "my-mq-exchange_C";

    /**
     * 队列名称
     */
    public static final String QUEUE_A = "QUEUE_A";
    public static final String QUEUE_B = "QUEUE_B";
    public static final String QUEUE_C = "QUEUE_C";

    /**
     * ROUTINGKEY_A
     */
    public static final String ROUTINGKEY_A = "spring-boot-routingKey_A";
    public static final String ROUTINGKEY_B = "spring-boot-routingKey_B";
    public static final String ROUTINGKEY_C = "spring-boot-routingKey_C";


    /**
     * 针对消费者配置
     * 1. 设置交换机类型
     * 2. 将队列绑定到交换机
     FanoutExchange: 将消息分发到所有的绑定队列，无routingkey的概念
     HeadersExchange ：通过添加属性key-value匹配
     DirectExchange:按照routingkey分发到指定队列
     TopicExchange:多关键字匹配
     */

    /**
     * 配置交换机A，类型为路由模式
     */
    @Bean
    public DirectExchange ExchangeA() {
        return new DirectExchange(EXCHANGE_A);
    }

    /**
     * 获取队列A
     */
    @Bean
    public Queue queueA() {
        return new Queue(QUEUE_A, true); //队列持久
    }

    /**
     * 获取队列B
     */
    @Bean
    public Queue queueB() {
        return new Queue(QUEUE_B, true); //队列持久
    }


    /**
     * 一个交换机可以绑定到多个消息队列，也就是消息通过一个交换机，可以分发到不同的队列当中去
     */

    /**
     * 交换机绑定队列A并指定routingkey_A
     * @return
     */
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queueA()).to(ExchangeA()).with(RabbitConfig.ROUTINGKEY_A);
    }

    /**
     * 交换机绑定队列B并指定routingkey_B
     * @return
     */
    @Bean
    public Binding bindingB() {
        return BindingBuilder.bind(queueB()).to(ExchangeA()).with(RabbitConfig.ROUTINGKEY_A);
    }

}
