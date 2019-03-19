package com.lipenglong.java.designmode.structure.proxy.staticproxy;

/**
 * study-notes
 * <p/>
 *
 * @author petric
 * @since 1.0-SNAPSHOT
 */
public class Cinema implements Movie {
    RealMovie realMovie;

    Cinema(RealMovie realMovie) {
        this.realMovie = realMovie;
    }

    @Override
    public void play() {
        System.out.println("爆米花广告");
        realMovie.play();
        System.out.println("要上映新电影广告");
    }
}
