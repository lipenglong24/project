package com.lipenglong.java.designmode.structure.flyweight;

import java.util.HashMap;

/**
 * study-notes
 * <p/>
 *
 * @author petric
 * @since 1.0-SNAPSHOT
 */
class FlyWeightFactory {
    private static FlyWeightFactory instance = new FlyWeightFactory();
    private HashMap<String, FlyWeight> map = new HashMap<>();

    static FlyWeightFactory getInstance() {
        return instance;
    }

    synchronized FlyWeight getFlyWeight(String name) {
        FlyWeight flyWeight = map.get(name);
        if (flyWeight == null) {
            flyWeight = new ConcreteFlyWeight(name);
            map.put(name, flyWeight);
        }
        return flyWeight;
    }
}
