package com.lipenglong.java.designmode.structure.decorator;

/**
 * study-notes
 * <p/>
 *
 * @author petric
 * @since 1.0-SNAPSHOT
 */
public class FullBorder extends Border {
    FullBorder(Display display) {
        super(display);
    }

    @Override
    public int getColumns() {
        return display.getColumns() + 2;
    }

    @Override
    public int getRows() {
        return display.getRows() + 2;
    }

    @Override
    public String getRowText(int row) {
        if (row == 0) {
            return "+" + makeLine('-', display.getColumns()) + "+";
        } else if (row == display.getRows() + 1) {
            return "+" + makeLine('-', display.getColumns()) + "+";
        } else {
            return "|" + display.getRowText(row - 1) + "|";
        }
    }

    private String makeLine(char c, int columns) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < columns; i++) {
            builder.append(c);
        }
        return builder.toString();
    }
}
