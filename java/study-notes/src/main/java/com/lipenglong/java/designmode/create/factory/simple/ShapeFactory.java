package com.lipenglong.java.designmode.create.factory.simple;

import com.lipenglong.java.designmode.create.factory.Circle;
import com.lipenglong.java.designmode.create.factory.Shape;
import com.lipenglong.java.designmode.create.factory.Square;

/**
 * study-notes
 * <p/>
 *
 * @author petric
 * @since 1.0-SNAPSHOT
 */
class ShapeFactory {
    static Shape getShape(String shapeName) {
        if (shapeName == null || "".equals(shapeName)) {
            return null;
        }
        switch (shapeName) {
            case "circle":
                return new Circle();
            case "square":
                return new Square();
        }
        return null;
    }
}
