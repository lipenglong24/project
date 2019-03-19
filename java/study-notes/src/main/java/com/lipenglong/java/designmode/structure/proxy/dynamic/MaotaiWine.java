package com.lipenglong.java.designmode.structure.proxy.dynamic;

/**
 * study-notes
 * <p/>
 *
 * @author petric
 * @since 1.0-SNAPSHOT
 */
public class MaotaiWine implements SellWine {
    @Override
    public void sellWine() {
        System.out.println("我卖的是茅台酒");
    }
}
