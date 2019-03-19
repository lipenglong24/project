package com.lipenglong.java.designmode.behavior.command;

import java.awt.*;

/**
 * study-notes
 * <p/>
 *
 * @author petric
 * @since 1.0-SNAPSHOT
 */
public class DrawCommand implements Command {
    private Drawable drawable;
    private Point position;

    DrawCommand(Drawable drawable, Point position) {
        this.drawable = drawable;
        this.position = position;
    }

    @Override
    public void execute() {
        drawable.draw(position.x, position.y);
    }
}
