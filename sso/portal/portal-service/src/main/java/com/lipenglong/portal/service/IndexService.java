package com.lipenglong.portal.service;

import com.lipenglong.portal.domain.Menu;

import java.util.List;

/**
 * index首页service接口类
 * <p/>
 * Created by lipenglong on 2017/10/23.
 */
public interface IndexService {
    List<Menu> queryMenuList();
}
