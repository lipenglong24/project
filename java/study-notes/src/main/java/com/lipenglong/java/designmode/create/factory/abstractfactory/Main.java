package com.lipenglong.java.designmode.create.factory.abstractfactory;

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
public class Main {
    public static void main(String[] args) {
        Factory factory = Factory.getFactory("com.lipenglong.java.designmode.create.factory.abstractfactory.listfactory.ListFactory");
        Link people = factory.createLink("人民日报", "http://www.people.com.cn");
        Link gmw = factory.createLink("光明日报", "http://www.gmw.cn");

        Link us_yahoo = factory.createLink("Yahoo!", "http://www.yahoo.com");
        Link jp_yahoo = factory.createLink("Yahoo! Japan", "http://www.yahoo.co.jp");
        Link excite = factory.createLink("Excite", "http://www.excite.com");
        Link google = factory.createLink("Google", "http://www.google.com");

        Tray trayNews = factory.createTray("日报");
        trayNews.add(people);
        trayNews.add(gmw);

        Tray trayYahoo = factory.createTray("Yahoo!");
        trayYahoo.add(us_yahoo);
        trayYahoo.add(jp_yahoo);

        Tray traySearch = factory.createTray("检索引擎");
        traySearch.add(trayYahoo);
        traySearch.add(excite);
        traySearch.add(google);

        Page page = factory.createPage("LinkPage", "lipenglong");
        page.add(trayNews);
        page.add(trayYahoo);
        page.add(traySearch);
        page.output();

    }
}
