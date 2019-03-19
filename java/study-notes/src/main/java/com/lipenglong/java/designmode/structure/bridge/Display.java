package com.lipenglong.java.designmode.structure.bridge;

/**
 * study-notes
 * <p/>
 *
 * @author petric
 * @since 1.0-SNAPSHOT
 */
public class Display {
    private DisplayImpl impl;

    Display(DisplayImpl impl) {
        this.impl = impl;
    }

    void open() {
        impl.rawOpen();
    }

    public void print() {
        impl.rawPrint();
    }

    void close() {
        impl.rawClose();
    }

    public final void display() {
        open();
        print();
        close();
    }
}
