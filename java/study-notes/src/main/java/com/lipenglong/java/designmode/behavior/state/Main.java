package com.lipenglong.java.designmode.behavior.state;

/**
 * study-notes
 * <p/>
 *
 * @author petric
 * @since 1.0-SNAPSHOT
 */
public class Main {
    public static void main(String[] args) {
        Context context = new SafeFrame("State sample");
        while (true) {
            for (int hour = 0; hour < 24; hour++) {
                context.setClock(hour);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
