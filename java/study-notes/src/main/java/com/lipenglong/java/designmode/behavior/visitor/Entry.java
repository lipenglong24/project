package com.lipenglong.java.designmode.behavior.visitor;

import java.util.Iterator;

/**
 * study-notes
 * <p/>
 *
 * @author petric
 * @since 1.0-SNAPSHOT
 */
public abstract class Entry implements Element {
    public abstract String getName();

    public abstract int getSize();

    public Entry add(Entry entry) {
        throw new RuntimeException("无法添加");
    }

    public Iterator<Entry> iterator() {
        throw new RuntimeException("无法遍历");
    }

    @Override
    public String toString() {
        return getName() + " (" + getSize() + ")";
    }
}
