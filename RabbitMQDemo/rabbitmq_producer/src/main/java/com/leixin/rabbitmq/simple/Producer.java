package com.leixin.rabbitmq.simple;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/*
简单模式
 */
public class Producer {
    public static void main(String[] args)  throws Exception{
        //创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //设置主机
        connectionFactory.setHost("192.168.122.131");
        //设置端口号
        connectionFactory.setPort(5672);
        //设置虚拟主机
        connectionFactory.setVirtualHost("/");
        //设置用户名
        connectionFactory.setUsername("admin");
        //设置密码
        connectionFactory.setPassword("123456");



        //创建连接
        Connection connection = connectionFactory.newConnection();

        //创建信道
        Channel channel = connection.createChannel();
        //声明队列
        /*
        参形：
         queue – 队列的名称 durable - 如果我们声明一个持久队列，则为真（该队列将在服务器重新启动后继续存在）
         exclusive - 如果我们声明一个独占队列，则为真（仅限于此连接）
         autoDelete – 如果我们声明一个自动删除队列，则为 true（服务器将在不再使用时将其删除）
         arguments – 队列的其他属性（构造参数）
        返回值：
        声明队列已成功声明的声明确认方法
         */
        channel.queueDeclare("simple_queue",true,false,false,null);

        //消息
        String message = "hello rabbit";

        /*
        发布消息。发布到不存在的交换器会导致通道级协议异常，从而关闭通道。
        如果资源驱动的警报生效，
         Channel#basicPublish的调用最终将被阻塞。
        参形：
            exchange - 将消息发布到的交换
            routingKey – 路由key,在简单模式中指定为队列的名字
            props - 消息的其他属性 - 路由标头等
            body - 消息正文
         */
        channel.basicPublish("","simple_queue",null,message.getBytes());
        //关闭资源
        channel.close();
        connection.close();


    }
}
