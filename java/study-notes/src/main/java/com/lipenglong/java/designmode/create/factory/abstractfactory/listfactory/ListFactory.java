package com.lipenglong.java.designmode.create.factory.abstractfactory.listfactory;

import com.lipenglong.java.designmode.create.factory.abstractfactory.factory.Factory;
import com.lipenglong.java.designmode.create.factory.abstractfactory.factory.Link;
import com.lipenglong.java.designmode.create.factory.abstractfactory.factory.Page;
import com.lipenglong.java.designmode.create.factory.abstractfactory.factory.Tray;

/**
 * study-notes
 * <p/>
 *
 * @author petric
 * @since 1.0-SNAPSHOT
 */
public class ListFactory extends Factory {
    @Override
    public Link createLink(String caption, String url) {
        return new ListLink(caption, url);
    }

    @Override
    public Tray createTray(String caption) {
        return new ListTray(caption);
    }

    @Override
    public Page createPage(String title, String author) {
        return new ListPage(title, author);
    }
}
