package com.lipenglong.java.designmode.structure.proxy.staticproxy;

/**
 * study-notes
 * <p/>
 *
 * @author petric
 * @since 1.0-SNAPSHOT
 */
public class RealMovie implements Movie {
    @Override
    public void play() {
        System.out.println("您正在观看电影《肖申克的救赎》");
    }
}
