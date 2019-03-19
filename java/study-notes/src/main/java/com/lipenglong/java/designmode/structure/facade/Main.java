package com.lipenglong.java.designmode.structure.facade;

/**
 * study-notes
 * <p/>
 *
 * @author petric
 * @since 1.0-SNAPSHOT
 */
public class Main {
    public static void main(String[] args) {
        Computer computer = new Computer();
        computer.start();
        computer.shutDown();
    }
}
