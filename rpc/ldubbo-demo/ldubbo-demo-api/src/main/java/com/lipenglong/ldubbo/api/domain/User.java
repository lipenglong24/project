package com.lipenglong.ldubbo.api.domain;

import java.io.Serializable;

/**
 * User
 * <p/>
 * Created by lipenglong on 2017/7/24.
 */
public class User implements Serializable {
    private static final long serialVersionUID = 2775326738215482673L;
    private Long id;
    private String name;
    private String password;
    private Integer role;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
