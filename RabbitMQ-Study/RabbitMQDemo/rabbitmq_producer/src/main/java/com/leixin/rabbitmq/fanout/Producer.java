package com.leixin.rabbitmq.fanout;


import com.leixin.rabbitmq.util.ConnectionUtil;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

//发布订阅模式（广播模式）的生产者
public class Producer {
    public static final String QUEUE_NAME1 = "fanout_queue1";
    public static final String QUEUE_NAME2 = "fanout_queue2";
    public static void main(String[] args) throws Exception{
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        /*
        exchange —交换机的名称
        type – 交换类型
        durable - 如果我们声明持久交换，则为真（交换将在服务器重新启动后继续存在）
        autoDelete – 如果服务器在不再使用时应删除交换，则为 true
        internal – 如果交换是内部的，则为 true，即不能由客户端直接发布。
        arguments – 交换的其他属性（构造参数）
         */
        channel.exchangeDeclare("test_fanout", BuiltinExchangeType.FANOUT,true,false,false,null);
        //声明队列
        channel.queueDeclare(QUEUE_NAME1,true,false,false,null);
        channel.queueDeclare(QUEUE_NAME2,true,false,false,null);

        //将队列和交换机绑定
        channel.queueBind(QUEUE_NAME1,"test_fanout","");
        channel.queueBind(QUEUE_NAME2,"test_fanout","");

        //设置发送的消息
        String message = "该条消息会被转发到两个队列";
        //发送消息
        channel.basicPublish("test_fanout","",null,message.getBytes());


        //关闭资源
        ConnectionUtil.closeResource(channel,connection);

    }
}
