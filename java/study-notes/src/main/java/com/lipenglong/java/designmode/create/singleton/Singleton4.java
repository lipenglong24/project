package com.lipenglong.java.designmode.create.singleton;

/**
 * 枚举类模式
 * <p/>
 *
 * @author petric
 * @since 1.0-SNAPSHOT
 */
public class Singleton4 {
    private static Singleton4 singleton4;

    private Singleton4() {
    }

    public static Singleton4 getInstance() {
        return Singleton4Enum.INSTANCE.getInstance();
    }

    private enum Singleton4Enum {
        INSTANCE;
        private Singleton4 singleton4;

        Singleton4Enum() {
            singleton4 = new Singleton4();
        }

        private Singleton4 getInstance() {
            return singleton4;
        }
    }
}
