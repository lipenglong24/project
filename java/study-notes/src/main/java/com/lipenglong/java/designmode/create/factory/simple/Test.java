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
public class Test {
    public static void main(String[] args) {
        Shape circle = ShapeFactory.getShape("circle");
        circle.draw();

        Shape square = ShapeFactory.getShape("square");
        square.draw();

        Shape circle2 = ShapeFactory2.getShape(Circle.class);
        circle2.draw();
        Shape square2 = ShapeFactory2.getShape(Square.class);
        square2.draw();
    }
}
