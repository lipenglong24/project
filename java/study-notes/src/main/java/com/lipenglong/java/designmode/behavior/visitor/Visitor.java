package com.lipenglong.java.designmode.behavior.visitor;

/**
 * study-notes
 * <p/>
 *
 * @author petric
 * @since 1.0-SNAPSHOT
 */
public interface Visitor {
    void visit(File file);

    void visit(Directory directory);
}
