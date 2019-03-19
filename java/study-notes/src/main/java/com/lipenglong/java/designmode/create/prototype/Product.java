package com.lipenglong.java.designmode.create.prototype;

/**
 * study-notes
 * <p/>
 *
 * @author petric
 * @since 1.0-SNAPSHOT
 */
interface Product extends Cloneable {
    void use(String s);

    Product createClone();
}
