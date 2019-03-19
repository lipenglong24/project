package com.lipenglong.java.designmode.create.factory.method;

import com.lipenglong.java.designmode.create.factory.Shape;
import com.lipenglong.java.designmode.create.factory.Square;

/**
 * study-notes
 * <p/>
 *
 * @author petric
 * @since 1.0-SNAPSHOT
 */
public class SquareFactory implements Factory {
    @Override
    public Shape getShape() {
        return new Square();
    }
}
