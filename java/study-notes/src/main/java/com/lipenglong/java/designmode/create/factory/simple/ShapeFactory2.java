package com.lipenglong.java.designmode.create.factory.simple;

import com.lipenglong.java.designmode.create.factory.Shape;

/**
 * study-notes
 * <p/>
 *
 * @author petric
 * @since 1.0-SNAPSHOT
 */
class ShapeFactory2 {
    static Shape getShape(Class clazz) {
        Shape shape = null;
        try {
            Object obj = Class.forName(clazz.getName()).newInstance();
            if (obj instanceof Shape) {
                shape = (Shape) obj;
            } else {
                throw new ClassCastException("类型错误");
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return shape;
    }
}
