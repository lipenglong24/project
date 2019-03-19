package com.lipenglong.java.designmode.structure.composite;

/**
 * study-notes
 * <p/>
 *
 * @author petric
 * @since 1.0-SNAPSHOT
 */
public class Main {
    public static void main(String[] args) {
        Company company = new ConcreteCompany("总公司");
        company.add(new HRDepartment("总公司人力资源部门"));
        company.add(new FinanceDepartment("总公司财务部门"));

        Company child = new ConcreteCompany("武汉分公司");
        child.add(new HRDepartment("武汉分公司人力资源部门"));
        child.add(new FinanceDepartment("武汉分公司财务部门"));
        company.add(child);

        System.out.println("---------------公司结构图-----------------");
        company.display();
        System.out.println("===============公司职能图=================");
        company.lineOfDuty();
    }
}
