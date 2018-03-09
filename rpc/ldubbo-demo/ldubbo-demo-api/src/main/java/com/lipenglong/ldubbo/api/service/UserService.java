package com.lipenglong.ldubbo.api.service;

import com.lipenglong.ldubbo.api.domain.User;

import java.util.List;

/**
 * 用户service接口类
 * <p/>
 * Created by lipenglong on 2017/7/24.
 */
public interface UserService {

    List<User> queryUserList();

    User getUserById(Long id);
}
