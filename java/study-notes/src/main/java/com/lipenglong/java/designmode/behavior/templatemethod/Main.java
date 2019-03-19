package com.lipenglong.java.designmode.behavior.templatemethod;

/**
 * study-notes
 * <p/>
 *
 * @author petric
 * @since 1.0-SNAPSHOT
 */
public class Main {
    public static void main(String[] args) {
        AbstractDisplay display1 = new CharDisplay('H');
        AbstractDisplay display2 = new StringDisplay("hello world");
        AbstractDisplay display3 = new StringDisplay("你好");

        display1.display();
        display2.display();
        display3.display();
    }
}
