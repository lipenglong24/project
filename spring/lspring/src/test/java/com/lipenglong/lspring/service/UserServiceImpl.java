package com.lipenglong.lspring.service;

import com.lipenglong.lspring.dao.UserDao;
import com.lipenglong.lspring.model.UserVO;
import com.lipenglong.lspring.annotation.Inject;
import com.lipenglong.lspring.annotation.Service;
import com.lipenglong.lspring.annotation.Transaction;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lipl
 * Date: 12-7-2
 * Time: 上午9:31
 * To change this template use File | Settings | File Templates.
 */
@Service(name = "userService")
public class UserServiceImpl implements UserService {

    @Inject(name = "userDao")
    private UserDao userDao;

    public UserServiceImpl() {
//        System.out.println("----------userServiceImpl init----------");
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transaction
    public List<UserVO> getUserList() {
        List<UserVO> list = this.userDao.getUserList();
        if(list != null) {
            for(UserVO user : list) {
                System.out.println("[id:" + user.getId() + ", name=" + user.getUserName() +
                    ", age=" + user.getAge() + ", birthday=" +
                    new SimpleDateFormat("yyyy-MM-dd").format(user.getBirthday()) +
                    ", height=" + user.getHeight() + ", identity=" + user.getIdentity() + "]");
            }
        }

//  int a = Integer.parseInt("a");
        return list;
    }

    public boolean addUser(UserVO userVO) {
        boolean flag = false;
        this.userDao.addUser(userVO);
//        int a = Integer.parseInt("a");
        flag = true;
        return flag;
    }

//    @Transaction
    public void print() {
        System.out.println("this method doesn't access database!");
    }


}
