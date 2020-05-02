package com.wheel.spring.cloud.domain;

/**
 * 用户模型
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 */
public class User {

    /**
     * ID
     */
    private Long id;

    /**
     * 用户名称
     */
    private String name;

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
