package com.segumentfault.spring.cloud.lesson10.user.service.provider.service;

import com.segumentfault.spring.cloud.lesson10.api.UserService;
import com.segumentfault.spring.cloud.lesson10.domain.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 内存实现{@link UserService}
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 0.0.1
 */
@Service("inMemoryUserService") // Bean 名称
public class InMemoryUserService implements UserService {

    private Map<Long, User> repository = new ConcurrentHashMap<>();

    {
        this.repository.put((long) 1, new User((long) 1, "test1"));
        this.repository.put((long) 2, new User((long) 2, "test2"));
    }

    @Override
    public boolean saveUser(User user) {
        return repository.put(user.getId(), user) == null;
    }

    @Override
    public List<User> findAll() {
        return new ArrayList(repository.values());
    }
}
