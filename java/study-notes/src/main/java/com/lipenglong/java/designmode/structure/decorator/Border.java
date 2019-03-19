package com.lipenglong.java.designmode.structure.decorator;

/**
 * study-notes
 * <p/>
 *
 * @author petric
 * @since 1.0-SNAPSHOT
 */
public abstract class Border extends Display {
    protected Display display;

    Border(Display display) {
        this.display = display;
    }
}
