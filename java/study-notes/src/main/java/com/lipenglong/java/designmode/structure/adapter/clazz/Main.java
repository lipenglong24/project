package com.lipenglong.java.designmode.structure.adapter.clazz;

/**
 * study-notes
 * <p/>
 *
 * @author petric
 * @since 1.0-SNAPSHOT
 */
public class Main {
    public static void main(String[] args) {
        Print p = new PrintBanner("hello world");
        p.printWeak();
        p.printStrong();
    }
}
