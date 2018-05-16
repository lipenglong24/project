package com.lipenglong.passport.dao;

import com.lipenglong.passport.domain.Users;
import org.apache.ibatis.annotations.Mapper;

/**
 * user dao mapper类
 * <p/>
 * Created by lipenglong on 2017/10/23.
 */
@Mapper
public interface UserDao {
    Users loadUserByUsername(String username);
}
