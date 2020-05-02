package com.wheel.spring.cloud.user.service.client.web.controller;

import com.wheel.spring.cloud.api.UserService;
import com.wheel.spring.cloud.domain.User;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * {@link UserService} 客户端 {@link RestController}
 *
 * 注意：官方建议 客户端和服务端不要同时实现 Feign 接口
 * 这里的代码只是一个说明，实际情况最好使用组合的方式，而不是继承
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 */
@RestController
@RefreshScope
public class UserServiceClientController implements UserService {

    @Resource   //@Autowired 不能实现热加载
    private UserService userService;

    // 通过方法继承，URL 映射 ："/user/save"
    @Override
    public boolean saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    // 通过方法继承，URL 映射 ："/user/find/all"  不会触发服务短路----- C、S都无短路
    @Override
    public List<User> findAll() {
        return userService.findAll();
    }

    // 通过方法继承，URL 映射 ："/user/list"  会触发服务短路 ---- 短路C无、S有
    @Override
    public List<User> getUsers() {
        return userService.getUsers();
    }
}
