package com.lipenglong.java.designmode.behavior.interpreter;

/**
 * study-notes
 * <p/>
 *
 * @author petric
 * @since 1.0-SNAPSHOT
 */
public abstract class Node {
    public abstract void parse(Context context) throws ParseException;
}
