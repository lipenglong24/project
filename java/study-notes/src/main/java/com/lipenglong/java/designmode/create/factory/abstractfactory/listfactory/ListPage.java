package com.lipenglong.java.designmode.create.factory.abstractfactory.listfactory;

import com.lipenglong.java.designmode.create.factory.abstractfactory.factory.Item;
import com.lipenglong.java.designmode.create.factory.abstractfactory.factory.Page;

/**
 * study-notes
 * <p/>
 *
 * @author petric
 * @since 1.0-SNAPSHOT
 */
public class ListPage extends Page {
    ListPage(String title, String author) {
        super(title, author);
    }

    @Override
    protected String makeHtml() {
        StringBuilder builder = new StringBuilder();
        builder.append("<html><head><title>").append(title).append("</title></head>\n");
        builder.append("<body></n>");
        builder.append("<h1>").append(title).append("</h1>\n");
        builder.append("<ul>\n");
        for (Item item : content) {
            builder.append(item.makeHtml());
        }
        builder.append("</ul>\n");
        builder.append("<hr><address>").append(author).append("</address>");
        builder.append("</body></html>\n");
        return builder.toString();
    }
}
