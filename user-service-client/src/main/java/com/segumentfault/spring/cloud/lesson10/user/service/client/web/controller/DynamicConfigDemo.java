package com.segumentfault.spring.cloud.lesson10.user.service.client.web.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author y15310
 * @Date 2020/4/25 10:04
 */
@RestController()
@RequestMapping("/demo")
@RefreshScope
public class DynamicConfigDemo {

    @Value("${provider.service.name}")
    private String providerServiceName;

    @Value("${user.service.name}")
    private String userServiceName;

    @GetMapping("/providerServiceName")
    public String test(){
        System.out.println("providerServiceName: " + providerServiceName);
        return providerServiceName;
    }

    @GetMapping("/userServiceName")
    public String test2(){
        System.out.println("userServiceName: " + userServiceName);
        return userServiceName;
    }
}
