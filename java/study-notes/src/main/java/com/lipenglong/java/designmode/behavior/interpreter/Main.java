package com.lipenglong.java.designmode.behavior.interpreter;

import java.util.ArrayList;
import java.util.List;

/**
 * study-notes
 * <p/>
 *
 * @author petric
 * @since 1.0-SNAPSHOT
 */
class Main {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("program end");
        list.add("program go end");
        list.add("program go right go right go right go right end");
        list.add("program repeat 4 go right end end");

        try {
            for (String text : list) {
                System.out.println("text = \"" + text + "\"");
                Node node = new ProgramNode();
                node.parse(new Context(text));
                System.out.println("node = " + node);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
