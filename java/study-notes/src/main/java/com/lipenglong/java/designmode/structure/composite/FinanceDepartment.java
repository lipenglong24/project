package com.lipenglong.java.designmode.structure.composite;

/**
 * study-notes
 * <p/>
 *
 * @author petric
 * @since 1.0-SNAPSHOT
 */
public class FinanceDepartment extends Company {

    FinanceDepartment(String name) {
        super(name);
    }

    @Override
    public void display() {
        System.out.println("部门：" + name);
    }

    @Override
    public void lineOfDuty() {
        System.out.println(name + "职责：负责财务管理");
    }
}
