package com.wheel.spring.cloud.user.service.web.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.wheel.spring.cloud.api.UserService;
import com.wheel.spring.cloud.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * 用户服务提供方 Controller
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 0.0.1
 */
@RestController
public class UserServiceProviderController implements UserService {

    @Autowired
    @Qualifier("inMemoryUserService") // 实现 Bean ： InMemoryUserService
    private UserService userService;

    @Value("${server.port}")
    private String port;

    private final static Random random = new Random();

    // 通过方法继承，URL 映射 ："/user/save"
    @Override
    public boolean saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @HystrixCommand(
            commandProperties = { // Command 配置
                    // 设置操作时间为 100 毫秒
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "100")
            },
            fallbackMethod = "fallbackForGetUsers" // 设置 fallback 方法
    )
    // 通过方法继承，URL 映射 ："/user/find/all"
    @Override
    public List<User> findAll() {
        List<User> users = userService.findAll();
        System.out.println("port:" +  port);
        return users;
    }

    /**
     * 获取所有用户列表
     *
     * @return
     */
    @HystrixCommand(
            commandProperties = { // Command 配置
                    // 设置操作时间为 100 毫秒
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "100")
            },
            fallbackMethod = "fallbackForGetUsers" // 设置 fallback 方法
    )
    @GetMapping("/user/list")
    public List<User> getUsers() {

        long executeTime = random.nextInt(200);

        // 通过休眠来模拟执行时间
        System.out.println("Execute Time : " + executeTime + " ms");

        int time = random.nextInt(1000*1000*1000);
        int j = 0;
        for (int i = 0; i < time; i++){
            j += j;
        }

        System.out.println("port:" +  port);

        return userService.findAll();
    }

    /**
     * {@link #getUsers()} 的 fallback 方法
     *
     * @return 空集合
     */
    public List<User> fallbackForGetUsers() {
        return Collections.emptyList();
    }

}
