package com.lipenglong.java.designmode.create.factory.abstractfactory.listfactory;

import com.lipenglong.java.designmode.create.factory.abstractfactory.factory.Item;
import com.lipenglong.java.designmode.create.factory.abstractfactory.factory.Tray;

/**
 * study-notes
 * <p/>
 *
 * @author petric
 * @since 1.0-SNAPSHOT
 */
public class ListTray extends Tray {
    ListTray(String caption) {
        super(caption);
    }

    @Override
    public String makeHtml() {
        StringBuilder builder = new StringBuilder();
        builder.append("<li>\n");
        builder.append(caption).append("\n");
        builder.append("<ul>\n");
        for (Item item : tray) {
            builder.append(item.makeHtml());
        }
        builder.append("</ul>\n");
        builder.append("</li>\n");
        return builder.toString();
    }
}
