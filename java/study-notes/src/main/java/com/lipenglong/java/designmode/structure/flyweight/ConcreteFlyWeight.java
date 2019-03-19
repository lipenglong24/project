package com.lipenglong.java.designmode.structure.flyweight;

/**
 * study-notes
 * <p/>
 *
 * @author petric
 * @since 1.0-SNAPSHOT
 */
public class ConcreteFlyWeight implements FlyWeight {
    private String name;

    ConcreteFlyWeight(String name) {
        this.name = name;
    }

    @Override
    public void action(String externalState) {
        System.out.println("name=" + name + "; externalState=" + externalState);
    }
}
