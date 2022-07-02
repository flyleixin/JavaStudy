package com.leixin.rabbitmq.util;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class ConnectionUtil {
    public static Connection getConnection () throws Exception{
        //创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //设置主机
        connectionFactory.setHost("");
        //设置端口号
        connectionFactory.setPort(5672);
        //设置虚拟主机
        connectionFactory.setVirtualHost("/");
        //设置用户名
        connectionFactory.setUsername("");
        //设置密码
        connectionFactory.setPassword("");
        //创建连接
        Connection connection = connectionFactory.newConnection();
        return  connection;
    }

    //关闭资源
    public static void closeResource(Channel channel , Connection connection) throws Exception {
        if(channel != null){
            channel.close();
        }
        if(connection != null){
            connection.close();
        }
    }


    public static void main(String[] args) throws Exception {
        Connection con = ConnectionUtil.getConnection();
        System.out.println(con);
        con.close();
    }
}
