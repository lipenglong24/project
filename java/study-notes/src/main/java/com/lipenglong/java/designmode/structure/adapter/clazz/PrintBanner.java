package com.lipenglong.java.designmode.structure.adapter.clazz;

import com.lipenglong.java.designmode.structure.adapter.Banner;

/**
 * study-notes
 * <p/>
 *
 * @author petric
 * @since 1.0-SNAPSHOT
 */
public class PrintBanner extends Banner implements Print {
    PrintBanner(String string) {
        super(string);
    }

    @Override
    public void printWeak() {
        showWithParen();
    }

    @Override
    public void printStrong() {
        showWithAster();
    }
}
