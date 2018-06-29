package com.lipenglong.portal.service.impl;

import com.lipenglong.portal.domain.Menu;
import com.lipenglong.portal.service.IndexService;
import com.lipenglong.portal.util.PropertiesUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.NumberUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * index首页service实现类
 * <p/>
 * Created by lipenglong on 2017/10/23.
 */
@Service
public class IndexServiceImpl implements IndexService {
    @Override
    public List<Menu> queryMenuList() {
        List<Menu> menuList = new ArrayList<Menu>();
        String fileName = "menu.properties";
        String[] menuCodes = PropertiesUtil.getValue(fileName, "menu_code").split(",");
        String[] menuNames = PropertiesUtil.getValue(fileName, "menu_name").split(",");
        String[] menuIcons = PropertiesUtil.getValue(fileName, "menu_icon").split(",");
        for (int i = 0; i < menuCodes.length; i++) {
            Menu menu = new Menu();
            menu.setId(NumberUtils.parseNumber(menuCodes[i], Integer.class));
            menu.setName(menuNames[i]);
            menu.setIcon(menuIcons[i]);

            List<Menu> itemList = new ArrayList<Menu>();
            String[] itemCodes = PropertiesUtil.getValue(fileName, "item_" + menu.getId() + "_code").split(",");
            String[] itemNames = PropertiesUtil.getValue(fileName, "item_" + menu.getId() + "_name").split(",");
            String[] itemUrls = PropertiesUtil.getValue(fileName, "item_" + menu.getId() + "_url").split(",");
            for (int j = 0; j < itemCodes.length; j++) {
                Menu item = new Menu();
                item.setId(NumberUtils.parseNumber(itemCodes[j], Integer.class));
                item.setName(itemNames[j]);
                item.setUrl(itemUrls[j]);
                itemList.add(item);
            }
            menu.setItemList(itemList);
            menuList.add(menu);
        }
        return menuList;
    }
}
