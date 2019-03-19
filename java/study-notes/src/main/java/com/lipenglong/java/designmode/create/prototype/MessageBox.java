package com.lipenglong.java.designmode.create.prototype;

/**
 * study-notes
 * <p/>
 *
 * @author petric
 * @since 1.0-SNAPSHOT
 */
class MessageBox implements Product {
    private char decorator;

    MessageBox(char decorator) {
        this.decorator = decorator;
    }

    @Override
    public void use(String s) {
        int length = s.getBytes().length;
        for (int i = 0; i < length + 4; i++) {
            System.out.print(decorator);
        }
        System.out.println();
        System.out.println(decorator + " " + s + " " + decorator);
        for (int i = 0; i < length + 4; i++) {
            System.out.print(decorator);
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
