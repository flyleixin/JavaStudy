package com.leixin.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope //当Nacos配置中的配置修改之后自动更新配置
public class ConfigClientController {
    @Value("${config.info}") //对应nacos配置:nacos-config-client-dev.yaml
    private String configInfo;

    @GetMapping("/config/info")
    public String getConfigInfo() {
        return configInfo;
    }
}
