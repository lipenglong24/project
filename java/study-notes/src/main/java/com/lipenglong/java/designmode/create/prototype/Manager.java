package com.lipenglong.java.designmode.create.prototype;

import java.util.HashMap;

/**
 * study-notes
 * <p/>
 *
 * @author petric
 * @since 1.0-SNAPSHOT
 */
public class Manager {
    private HashMap<String, Product> showcase = new HashMap<>();

    void register(String name, Product product) {
        showcase.put(name, product);
    }

    public Product create(String productName) {
        Product product = showcase.get(productName);
        return product.createClone();
    }
}
