package com.lipenglong.java.designmode.behavior.strategy;

/**
 * study-notes
 * <p/>
 *
 * @author petric
 * @since 1.0-SNAPSHOT
 */
public interface Strategy {
    Hand nextHand();

    void study(boolean win);
}
