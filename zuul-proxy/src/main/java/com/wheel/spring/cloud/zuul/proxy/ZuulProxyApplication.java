package com.wheel.spring.cloud.zuul.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.context.refresh.ContextRefresher;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Set;

/**
 * Zuul 代理引导类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 */
@EnableZuulProxy
@SpringCloudApplication
public class ZuulProxyApplication {

    private final ContextRefresher contextRefresher;

    @Autowired
    public ZuulProxyApplication(ContextRefresher contextRefresher) {
        this.contextRefresher = contextRefresher;
    }
    @Scheduled(fixedRate = 1000L)
    public void update() {

        Set<String> keys = contextRefresher.refresh();

        if (!keys.isEmpty()) {
            System.out.println("本次更新的配置项: " + keys);
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(ZuulProxyApplication.class, args);
    }

}
