package com.lipenglong.java.designmode.behavior.state;

/**
 * study-notes
 * <p/>
 *
 * @author petric
 * @since 1.0-SNAPSHOT
 */
public interface Context {
    void setClock(int hour);

    void changeState(State state);

    void recordLog(String msg);

    void callSecurityCenter(String msg);
}
