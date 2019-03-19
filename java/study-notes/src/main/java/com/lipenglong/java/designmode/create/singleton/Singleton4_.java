package com.lipenglong.java.designmode.create.singleton;

/**
 * 枚举单例类
 * <p/>
 *
 * @author petric
 * @since 1.0-SNAPSHOT
 */
public enum Singleton4_ {
    INSTANCE;

    public void doSomething() {
        System.out.println("do something");
    }

    public static void main(String[] args) {
        Singleton4_.INSTANCE.doSomething();
    }
}
