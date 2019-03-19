package com.lipenglong.java.designmode.create.prototype;

/**
 * study-notes
 * <p/>
 *
 * @author petric
 * @since 1.0-SNAPSHOT
 */
class UnderlinePen implements Product {
    private char ulChar;

    UnderlinePen(char ulChar) {
        this.ulChar = ulChar;
    }

    @Override
    public void use(String s) {
        int length = s.getBytes().length;
        System.out.println("\"" + s + "\"");
        for (int i = 0; i < length + 2; i++) {
            System.out.print(ulChar);
        }
        System.out.println();
    }

    @Override
    public Product createClone() {
        Product p = null;
        try {
            p = (Product) clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return p;
    }
}
