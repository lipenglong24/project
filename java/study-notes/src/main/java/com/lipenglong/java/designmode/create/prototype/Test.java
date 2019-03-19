package com.lipenglong.java.designmode.create.prototype;

/**
 * study-notes
 * <p/>
 *
 * @author petric
 * @since 1.0-SNAPSHOT
 */
public class Test {
    public static void main(String[] args) {
        Manager manager = new Manager();
        UnderlinePen underlinePen = new UnderlinePen('~');
        MessageBox messageBox = new MessageBox('*');
        manager.register("Strong message", underlinePen);
        manager.register("Warning box", messageBox);

        Product p1 = manager.create("Strong message");
        p1.use("hello world");
        Product p2 = manager.create("Warning box");
        p2.use("hello world");

        messageBox.use("hello nima");
    }
}
