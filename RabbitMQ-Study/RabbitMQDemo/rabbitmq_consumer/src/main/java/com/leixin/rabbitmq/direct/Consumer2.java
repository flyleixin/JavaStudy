package com.leixin.rabbitmq.direct;

import com.leixin.rabbitmq.util.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

public class Consumer2 {
    public static void main(String[] args) throws Exception{
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        DefaultConsumer defaultConsumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("consumerTag = " + consumerTag);
                System.out.println("envelope.getExchange() = " + envelope.getExchange());
                System.out.println("envelope.getRoutingKey() = " + envelope.getRoutingKey());
                System.out.println("envelope.getDeliveryTag() = " + envelope.getDeliveryTag());
                System.out.println("new String(body) = " + new String(body));

            }
        };

        channel.basicConsume("direct_queue2",true,defaultConsumer);

    }
}
