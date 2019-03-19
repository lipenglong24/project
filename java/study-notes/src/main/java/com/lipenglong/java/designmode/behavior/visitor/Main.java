package com.lipenglong.java.designmode.behavior.visitor;

/**
 * study-notes
 * <p/>
 *
 * @author petric
 * @since 1.0-SNAPSHOT
 */
public class Main {
    public static void main(String[] args) {
        Directory rootDir = new Directory("usr");
        Directory binDir = new Directory("bin");
        Directory javaDir = new Directory("java");
        Directory localDir = new Directory("local");
        rootDir.add(binDir).add(javaDir).add(localDir);
        binDir.add(new File("vim", 2000));
        binDir.add(new File("mysql", 3000));
        javaDir.add(new File("default", 1024));
        rootDir.accept(new ListVisitor());
    }
}
