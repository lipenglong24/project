package com.lipenglong.lspring.action;

import com.lipenglong.lspring.model.UserVO;
import com.lipenglong.lspring.service.UserService;
import com.lipenglong.lspring.annotation.Action;
import com.lipenglong.lspring.annotation.Inject;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lipl
 * Date: 12-7-4
 * Time: 上午9:41
 * To change this template use File | Settings | File Templates.
 */
@Action(name="userAction")
public class UserAction {
    @Inject
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public UserAction() {
//        System.out.println("----------userAction init----------");
    }

    public List<UserVO> getUserList() {
        return this.userService.getUserList();
    }

    public boolean addUser() {
        UserVO userVO = new UserVO();
        return this.userService.addUser(userVO);
    }
}
