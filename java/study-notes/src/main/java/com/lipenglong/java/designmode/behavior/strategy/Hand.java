package com.lipenglong.java.designmode.behavior.strategy;

/**
 * study-notes
 * <p/>
 *
 * @author petric
 * @since 1.0-SNAPSHOT
 */
public class Hand {
    public static final int SHITOU = 0;
    public static final int JIANDAO = 1;
    public static final int BU = 1;

    public static final Hand[] hand = {new Hand(SHITOU), new Hand(JIANDAO), new Hand(BU)};

    private static final String[] name = {"石头", "剪刀", "布"};
    private int handValue;

    public Hand(int handValue) {
        this.handValue = handValue;
    }

    public static Hand getHand(int handValue) {
        return hand[handValue];
    }

    public boolean isStrongerThan(Hand h) {
        return fight(h) == 1;
    }

    public boolean isWeakerThan(Hand h) {
        return fight(h) == -1;
    }

    private int fight(Hand h) {
        if (this == h) {
            return 0;
        } else if ((this.handValue + 1) % 3 == h.handValue) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        return "Hand{" +
                "handValue=" + name[handValue] +
                '}';
    }
}
