package com.lipenglong.passport.service;

import com.lipenglong.passport.dao.UserDao;
import com.lipenglong.passport.domain.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * UserDetailsService实现类
 * <p/>
 * Created by lipenglong on 2017/10/23.
 */
@Service
public class UserServiceImpl implements UserDetailsService {
    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users dbUser = userDao.loadUserByUsername(username);
        List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ADMIN"));
        User user = new User(dbUser.getUsername(), dbUser.getPassword(), authorities);
        return user;
    }
}
