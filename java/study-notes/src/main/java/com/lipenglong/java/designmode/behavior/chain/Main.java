package com.lipenglong.java.designmode.behavior.chain;

/**
 * study-notes
 * <p/>
 *
 * @author petric
 * @since 1.0-SNAPSHOT
 */
public class Main {
    public static void main(String[] args) {
        Support a = new NoSupport("a");
        Support b = new LimitSupport("b", 10);
        Support c = new OddSupport("c");
        Support d = new SpecialSupport("d", 16);
        a.setNext(b).setNext(c).setNext(d);

        for (int i = 0; i < 20; i++) {
            a.support(new Trouble(i));
        }
    }
}
