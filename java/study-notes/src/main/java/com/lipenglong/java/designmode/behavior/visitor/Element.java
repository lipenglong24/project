package com.lipenglong.java.designmode.behavior.visitor;

/**
 * study-notes
 * <p/>
 *
 * @author petric
 * @since 1.0-SNAPSHOT
 */
public interface Element {
    void accept(Visitor visitor);
}
