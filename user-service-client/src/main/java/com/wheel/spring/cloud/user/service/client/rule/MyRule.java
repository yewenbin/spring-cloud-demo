package com.wheel.spring.cloud.user.service.client.rule;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.Server;

import java.util.List;
import java.util.Random;

/**
 * 自定义{@link IRule} 实现，永远选择最后一台可达服务器
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 0.0.1
 */
public class MyRule extends AbstractLoadBalancerRule {

    private Random random = new Random();

    @Override
    public void initWithNiwsConfig(IClientConfig clientConfig) {

    }

    @Override
    public Server choose(Object key) {

        ILoadBalancer loadBalancer = getLoadBalancer();

        //获取所有可达服务器列表
        List<Server> servers = loadBalancer.getReachableServers();
        if (servers.isEmpty()) {
            return null;
        }

        int index = random.nextInt(3);
        // 永远选择最后一台可达服务器
        Server targetServer = servers.get(index);
        return targetServer;
    }

}
