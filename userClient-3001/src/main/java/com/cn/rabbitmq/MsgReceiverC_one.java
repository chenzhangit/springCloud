package com.cn.rabbitmq;

import com.cn.config.RabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @auther
 * @date 2021-08-31-17:36
 */
@Component
public class MsgReceiverC_one {

    @RabbitListener(queues = RabbitConfig.QUEUE_A)
    public void listenerOneA(String content) {
        System.out.println("处理器one接收处理队列A当中的消息： " + content);
    }

    @RabbitListener(queues = RabbitConfig.QUEUE_A)
    public void listenerTwoA(String content) {
        System.out.println("处理器two接收处理队列A当中的消息： " + content);
    }

    @RabbitListener(queues = RabbitConfig.QUEUE_B)
    public void listenerOneB(String content) {
        System.out.println("处理器one接收处理队列B当中的消息： " + content);
    }

    @RabbitListener(queues = RabbitConfig.QUEUE_B)
    public void listenerTwoB(String content) {
        System.out.println("处理器two接收处理队列B当中的消息： " + content);
    }

}
