package com.lipenglong.java.designmode.create.factory.method;

import com.lipenglong.java.designmode.create.factory.Circle;
import com.lipenglong.java.designmode.create.factory.Shape;

/**
 * study-notes
 * <p/>
 *
 * @author petric
 * @since 1.0-SNAPSHOT
 */
public class CircleFactory implements Factory {
    @Override
    public Shape getShape() {
        return new Circle();
    }
}
