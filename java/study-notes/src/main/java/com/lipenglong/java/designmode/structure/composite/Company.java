package com.lipenglong.java.designmode.structure.composite;

/**
 * study-notes
 * <p/>
 *
 * @author petric
 * @since 1.0-SNAPSHOT
 */
public abstract class Company {
    protected String name;

    Company(String name) {
        this.name = name;
    }

    public void add(Company company) throws RuntimeException {
        throw new RuntimeException("不能添加子公司");
    }

    public void remove(Company company) throws RuntimeException {
        throw new RuntimeException("无删除子公司操作");
    }

    public abstract void display();

    public abstract void lineOfDuty();
}
