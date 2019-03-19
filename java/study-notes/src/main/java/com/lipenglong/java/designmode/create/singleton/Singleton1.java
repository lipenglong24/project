package com.lipenglong.java.designmode.create.singleton;

/**
 * 饿汉模式
 * <p/>
 *
 * @author petric
 * @since 1.0-SNAPSHOT
 */
public class Singleton1 {
    private static Singleton1 singleton1 = new Singleton1();

    private Singleton1() {
    }

    public static Singleton1 getInstance() {
        return singleton1;
    }
}
