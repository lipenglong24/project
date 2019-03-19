package com.lipenglong.java.designmode.structure.bridge;

/**
 * study-notes
 * <p/>
 *
 * @author petric
 * @since 1.0-SNAPSHOT
 */
class CountDisplay extends Display {
    CountDisplay(DisplayImpl impl) {
        super(impl);
    }

    void multiDisplay(int times) {
        open();
        for (int i = 0; i < times; i++) {
            print();
        }
        close();
    }
}
