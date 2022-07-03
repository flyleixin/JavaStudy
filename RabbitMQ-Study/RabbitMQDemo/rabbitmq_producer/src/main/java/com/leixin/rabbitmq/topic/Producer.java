package com.leixin.rabbitmq.topic;


import com.leixin.rabbitmq.util.ConnectionUtil;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

//路由模式(定向模式)
public class Producer {
    public static final String EXCHANGE_NAME = "topic_exchange";
    public static final String QUEUE_NAME1 = "topic_queue1";
    public static final String QUEUE_NAME2 = "topic_queue2";

    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.getConnection();
        //创建信道
        Channel channel = connection.createChannel();
        //声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC,true,false,false,null);
        //声明两个队列
        channel.queueDeclare(QUEUE_NAME1,true,false,false,null);
        channel.queueDeclare(QUEUE_NAME2,true,false,false,null);
        //将队列绑定到交换机里面

        /*
        # 匹配0个或多个单词
        * 匹配一个单词，不多不少，恰好一个
         */
        //发送消息时路由key是(#.leixin)的消息将转发到这条队列(QUEUE_NAME1)里面
        channel.queueBind(QUEUE_NAME1,EXCHANGE_NAME,"#.leixin",null);
        //发送消息时路由key是(leixin.#)的消息将转发到这条队列(QUEUE_NAME2)里面
        channel.queueBind(QUEUE_NAME2,EXCHANGE_NAME,"leixin.#",null);


        String message = "该条消息只发给第一个队列";
        channel.basicPublish(EXCHANGE_NAME,"www.www.leixin",null,message.getBytes());

        String message2 = "该条信息只会进入第二个队列";
        channel.basicPublish(EXCHANGE_NAME,"leixin.hello",null,message2.getBytes());

        ConnectionUtil.closeResource(channel,connection);




    }
}
