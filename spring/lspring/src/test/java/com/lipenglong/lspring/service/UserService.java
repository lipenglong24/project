package com.lipenglong.lspring.service;

import com.lipenglong.lspring.model.UserVO;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lipl
 * Date: 12-7-2
 * Time: 下午9:59
 * To change this template use File | Settings | File Templates.
 */
public interface UserService {
    public List<UserVO> getUserList();

    public boolean addUser(UserVO userVO);

    public void print();
}
