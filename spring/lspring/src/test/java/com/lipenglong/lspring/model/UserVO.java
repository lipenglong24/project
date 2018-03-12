package com.lipenglong.lspring.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: lipl
 * Date: 12-6-29
 * Time: 下午8:55
 * To change this template use File | Settings | File Templates.
 */
public class UserVO implements Serializable {
    private long id;
    private String userName;
    private int age;
    private Date birthday;
    private float height;
    private transient long identity;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public long getIdentity() {
        return identity;
    }

    public void setIdentity(long identity) {
        this.identity = identity;
    }

    public String toString() {
        return "userName:" + userName + "\n"
                + "age:" + age + "\n"
                + "birthday:" + new SimpleDateFormat("yyyy-MM-dd").format(birthday) + "\n"
                + "height:" + height + "\n"
                + "identity:" + getIdentity();
    }
}
