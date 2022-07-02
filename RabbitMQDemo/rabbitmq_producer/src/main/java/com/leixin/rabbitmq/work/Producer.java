package com.leixin.rabbitmq.work;

import com.leixin.rabbitmq.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class Producer {
    public static final String QUEUE_WORK_MQ = "work_queue";
    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.getConnection();

        //创建信道
        Channel channel = connection.createChannel();
        //声明队列
        channel.queueDeclare(QUEUE_WORK_MQ,true,false,false,null);
        //一次性发送多条信息
        for (int i = 1; i <= 100; i++) {
            String message = "消息"+i;
            channel.basicPublish("",QUEUE_WORK_MQ,null,message.getBytes());

        }
        ConnectionUtil.closeResource(channel,connection);
    }
}
