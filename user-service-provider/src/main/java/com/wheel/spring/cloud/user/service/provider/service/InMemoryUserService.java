package com.wheel.spring.cloud.user.service.provider.service;

import com.wheel.spring.cloud.api.UserService;
import com.wheel.spring.cloud.domain.User;
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

    @Override
    public List<User> getUsers() {
        return null;
    }
}
