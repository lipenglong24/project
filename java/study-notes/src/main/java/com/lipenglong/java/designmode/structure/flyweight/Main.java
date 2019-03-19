package com.lipenglong.java.designmode.structure.flyweight;

/**
 * study-notes
 * <p/>
 *
 * @author petric
 * @since 1.0-SNAPSHOT
 */
public class Main {
    public static void main(String[] args) {
        FlyWeightFactory factory = FlyWeightFactory.getInstance();
        FlyWeight flyWeight = factory.getFlyWeight("jone");
        flyWeight.action("nice to meet you");
    }
}
