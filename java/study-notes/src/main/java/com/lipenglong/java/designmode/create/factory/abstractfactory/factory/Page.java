package com.lipenglong.java.designmode.create.factory.abstractfactory.factory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * study-notes
 * <p/>
 *
 * @author petric
 * @since 1.0-SNAPSHOT
 */
public abstract class Page {
    protected String title;
    protected String author;
    protected List<Item> content = new ArrayList<>();

    public Page(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public void add(Item item) {
        content.add(item);
    }

    public void output() {
        try {
            String fileName = title + ".html";
            Writer writer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(fileName), StandardCharsets.UTF_8));
            writer.write(this.makeHtml());
            writer.close();
            System.out.println(fileName + "编写完成。");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected abstract String makeHtml();
}
