package com.rule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
public class MyRuleConfig {
    //返回随机的实现类对象
    @Bean
    public IRule getRule(){
        return new RandomRule();
    }
}
