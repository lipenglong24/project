package com.lipenglong.java.designmode.structure.composite;

/**
 * study-notes
 * <p/>
 *
 * @author petric
 * @since 1.0-SNAPSHOT
 */
public class HRDepartment extends Company {
    HRDepartment(String name) {
        super(name);
    }

    @Override
    public void display() {
        System.out.println("部门：" + name);
    }

    @Override
    public void lineOfDuty() {
        System.out.println(name + "职责：负责招聘");
    }
}
