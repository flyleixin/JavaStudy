package com.leixin.rabbitmq.direct;


import com.leixin.rabbitmq.util.ConnectionUtil;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

//路由模式(定向模式)
public class Producer {
    public static final String EXCHANGE_NAME = "direct_exchange";
    public static final String QUEUE_NAME1 = "direct_queue1";
    public static final String QUEUE_NAME2 = "direct_queue2";

    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.getConnection();
        //创建信道
        Channel channel = connection.createChannel();
        //声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT,true,false,false,null);
        //声明两个队列
        channel.queueDeclare(QUEUE_NAME1,true,false,false,null);
        channel.queueDeclare(QUEUE_NAME2,true,false,false,null);
        //将队列绑定到交换机里面
        //发送消息时路由key是error的消息将转发到这条队列(QUEUE_NAME1)里面
        channel.queueBind(QUEUE_NAME1,EXCHANGE_NAME,"error",null);
        //发送消息时路由key是info,warning,error的消息将转发到这条队列(QUEUE_NAME2)里面
        channel.queueBind(QUEUE_NAME2,EXCHANGE_NAME,"info",null);
        channel.queueBind(QUEUE_NAME2,EXCHANGE_NAME,"warning",null);
        channel.queueBind(QUEUE_NAME2,EXCHANGE_NAME,"error",null);

        String message = "该条消息会同时发给两个队列";
        channel.basicPublish(EXCHANGE_NAME,"error",null,message.getBytes());

        String message2 = "该条信息只会进入第二个队列";
        channel.basicPublish(EXCHANGE_NAME,"info",null,message2.getBytes());

        ConnectionUtil.closeResource(channel,connection);




    }
}
