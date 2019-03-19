package com.lipenglong.java.designmode.create.factory.abstractfactory.factory;

/**
 * study-notes
 * <p/>
 *
 * @author petric
 * @since 1.0-SNAPSHOT
 */
public abstract class Item {
    protected String caption;

    Item(String caption) {
        this.caption = caption;
    }

    public abstract String makeHtml();
}
