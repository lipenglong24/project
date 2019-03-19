package com.lipenglong.java.designmode.structure.proxy.staticproxy;

/**
 * study-notes
 * <p/>
 *
 * @author petric
 * @since 1.0-SNAPSHOT
 */
public class Main {
    public static void main(String[] args) {
        RealMovie realMovie = new RealMovie();
        Movie movie = new Cinema(realMovie);

        movie.play();
    }
}
