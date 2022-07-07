package com.leixin.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 获取RestTemplate 的一个配置类
 * RestTemplate提供了多种便捷访问远程Http服务的方法，
 * 是一种简单便捷的访问Restful服务模板类，
 * 是Spring 提供的用于访问Rest服务的客户端模板工具集
 */

/**
 * 使用RestTemplate访问Restful接口非常的简单粗暴无脑。
 * （url，requestMap，ResponseBean.class）
 * 这三个参数分别代表
 * REST请求地址、请求参数、Http响应转换被转换成的对象类型。
 */
@SpringBootConfiguration
public class getRestTemplateConfig {

    @Bean
    @LoadBalanced//开启负载均衡
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
