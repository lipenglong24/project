package com.lipenglong.ldubbo.provider.service;

import com.lipenglong.ldubbo.api.domain.User;
import com.lipenglong.ldubbo.api.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * UserServiceImpl
 * <p/>
 * Created by lipenglong on 2017/7/24.
 */
@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Override
    public List<User> queryUserList() {
        List<User> result = new ArrayList<User>();

        User user = new User();
        user.setId(1L);
        user.setName("user1");
        user.setPassword("****");
        user.setRole(1);
        result.add(user);

        return result;
    }

    public User getUserById(Long id) {
        User user = new User();
        user.setId(id);
        user.setName("user" + id);
        user.setPassword("****");
        user.setRole(1);
        return user;
    }
}
