package com.lipenglong.java.designmode.behavior.observer;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

/**
 * study-notes
 * <p/>
 *
 * @author petric
 * @since 1.0-SNAPSHOT
 */
public class RandomNumberGenerator extends NumberGenerator {
    private Random random = new Random();
    private int number;

    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public void execute() {
        for (int i = 0; i < 10; i++) {
            number = random.nextInt(50);
            notifyObservers();
        }
    }
}
