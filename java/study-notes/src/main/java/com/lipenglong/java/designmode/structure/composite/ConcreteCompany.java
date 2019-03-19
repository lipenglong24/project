package com.lipenglong.java.designmode.structure.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * study-notes
 * <p/>
 *
 * @author petric
 * @since 1.0-SNAPSHOT
 */
public class ConcreteCompany extends Company {
    private List<Company> childrenCompany = new ArrayList<>();

    ConcreteCompany(String name) {
        super(name);
    }

    @Override
    public void add(Company company) throws RuntimeException {
        childrenCompany.add(company);
    }

    @Override
    public void remove(Company company) throws RuntimeException {
        childrenCompany.remove(company);
    }

    @Override
    public void display() {
        System.out.println("公司：" + name);
        for (Company company : childrenCompany) {
            company.display();
        }
    }

    @Override
    public void lineOfDuty() {
        for (Company company : childrenCompany) {
            company.lineOfDuty();
        }
    }
}
