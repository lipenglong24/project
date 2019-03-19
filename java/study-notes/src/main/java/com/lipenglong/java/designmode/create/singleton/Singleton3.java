package com.lipenglong.java.designmode.create.singleton;

/**
 * 静态内部类模式
 * <p/>
 *
 * @author petric
 * @since 1.0-SNAPSHOT
 */
public class Singleton3 {
    private Singleton3() {
    }

    private static class Singleton3Holder {
        private static final Singleton3 singleton3 = new Singleton3();
    }

    public static Singleton3 getInstance() {
        return Singleton3Holder.singleton3;
    }
}
