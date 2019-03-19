package com.lipenglong.java.designmode.create.factory.abstractfactory.listfactory;

import com.lipenglong.java.designmode.create.factory.abstractfactory.factory.Link;

/**
 * study-notes
 * <p/>
 *
 * @author petric
 * @since 1.0-SNAPSHOT
 */
public class ListLink extends Link {
    ListLink(String caption, String url) {
        super(caption, url);
    }

    @Override
    public String makeHtml() {
        return "    <li><a href=\"" + url + "\">" + caption + "</a></li>\n";
    }
}
