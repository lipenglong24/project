package com.lipenglong.java.designmode.structure.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * study-notes
 * <p/>
 *
 * @author petric
 * @since 1.0-SNAPSHOT
 */
public class Main {
    public static void main(String[] args) {
        MaotaiWine maotaiWine = new MaotaiWine();
        InvocationHandler guitaiA = new GuitaiA(maotaiWine);
        SellWine sellWine = (SellWine) Proxy.newProxyInstance(MaotaiWine.class.getClassLoader(),
                MaotaiWine.class.getInterfaces(), guitaiA);
        sellWine.sellWine();
        System.out.println(sellWine.getClass());

        SellCigarette yunYan = new YunyanCigarette();
        InvocationHandler guitaiB = new GuitaiA(yunYan);
        SellCigarette sellCigarette = (SellCigarette) Proxy.newProxyInstance(YunyanCigarette.class.getClassLoader(),
                YunyanCigarette.class.getInterfaces(), guitaiB);
        sellCigarette.sell();
        System.out.println(sellCigarette.getClass());
    }
}
