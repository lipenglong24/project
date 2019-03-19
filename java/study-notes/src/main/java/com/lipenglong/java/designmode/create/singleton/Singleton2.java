package com.lipenglong.java.designmode.create.singleton;

/**
 * 双重检查枷锁模式
 * <p/>
 *
 * @author petric
 * @since 1.0-SNAPSHOT
 */
public class Singleton2 {
    private volatile static Singleton2 singleton2;

    private Singleton2() {
    }

    public static Singleton2 getInstance() {
        if (singleton2 == null) {
            synchronized (Singleton2.class) {
                if (singleton2 == null) {
                    singleton2 = new Singleton2();
                }
            }
        }
        return singleton2;
    }
}
