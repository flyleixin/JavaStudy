package com.leixin.rabbitmq.simple;


import com.rabbitmq.client.*;


import java.io.IOException;

/*
简单模式的消费者
 */
public class Consumer {
    public static void main(String[] args) throws Exception{
        //创建工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.122.131");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("123456");
        Connection connection = connectionFactory.newConnection();
        //创建信道
        Channel channel = connection.createChannel();
        /*
        作为一个消费者，可以不声明队列
         */
        channel.queueDeclare("simple_queue",true,false,false,null);

        DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                //获取消费者的标识
                System.out.println("consumerTag = "+consumerTag);
                //获取交换机的名字
                System.out.println("envelope.getExchange = " +envelope.getExchange());
                //获取路由key
                System.out.println("envelope.getRoutingKey" + envelope.getRoutingKey());
                //获取当前是第几条标识
                System.out.println("envelope.getDeliverTag = " + envelope.getDeliveryTag());
                //获取其他属性
                System.out.println("properties = " + properties);
                //获取消息
                System.out.println("new String(body) = " + new String(body));

            }
        };

        channel.basicConsume("simple_queue",true,defaultConsumer);

    }


}
