package com.lipenglong.ldubbo.config;

import java.io.Serializable;

/**
 * ldubbo config配置父类
 * <p/>
 * Created by lipenglong on 2017/7/25.
 */
public abstract class AbstractConfig implements Serializable {
    private static final long serialVersionUID = 4408739851966423744L;
    protected String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
