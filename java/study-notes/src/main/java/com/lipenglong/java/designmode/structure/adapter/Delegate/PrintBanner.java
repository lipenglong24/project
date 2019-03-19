package com.lipenglong.java.designmode.structure.adapter.Delegate;

import com.lipenglong.java.designmode.structure.adapter.Banner;

/**
 * study-notes
 * <p/>
 *
 * @author petric
 * @since 1.0-SNAPSHOT
 */
public class PrintBanner extends Print {
    Banner banner;

    PrintBanner(String string) {
        this.banner = new Banner(string);
    }

    @Override
    public void printWeak() {
        banner.showWithParen();
    }

    @Override
    public void printStrong() {
        banner.showWithAster();
    }
}
