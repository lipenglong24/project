package com.lipenglong.lspring.dao;

import com.lipenglong.lspring.model.UserVO;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lipl
 * Date: 12-7-2
 * Time: 下午10:03
 * To change this template use File | Settings | File Templates.
 */
public interface UserDao {
    public List<UserVO> getUserList();

    void addUser(UserVO userVO);
}
