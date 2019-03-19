package com.lipenglong.java.designmode.behavior.iterator;

/**
 * study-notes
 * <p/>
 *
 * @author petric
 * @since 1.0-SNAPSHOT
 */
public interface Iterator<T> {
    boolean hasNext();

    T next();
}
