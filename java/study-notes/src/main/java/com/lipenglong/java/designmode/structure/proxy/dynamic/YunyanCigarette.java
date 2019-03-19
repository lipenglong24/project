package com.lipenglong.java.designmode.structure.proxy.dynamic;

/**
 * study-notes
 * <p/>
 *
 * @author petric
 * @since 1.0-SNAPSHOT
 */
public class YunyanCigarette implements SellCigarette {
    @Override
    public void sell() {
        System.out.println("我卖的是云烟");
    }
}
