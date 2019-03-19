package com.lipenglong.java.designmode.structure.bridge;

/**
 * study-notes
 * <p/>
 *
 * @author petric
 * @since 1.0-SNAPSHOT
 */
public class Main {
    public static void main(String[] args) {
        Display d1 = new Display(new StringDisplayImpl("hello, china."));
        Display d2 = new Display(new StringDisplayImpl("hello, world."));
        CountDisplay d3 = new CountDisplay(new StringDisplayImpl("hello, universe."));

        d1.display();
        d2.display();
        d3.display();
        d3.multiDisplay(3);
    }
}
