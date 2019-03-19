package com.lipenglong.java.designmode.create.builder;

/**
 * study-notes
 * <p/>
 *
 * @author petric
 * @since 1.0-SNAPSHOT
 */
class MealA extends MealBuilder {

    @Override
    void buildFood() {
        meal.setFood("香辣鸡腿堡");
    }

    @Override
    void buildDrink() {
        meal.setDrink("可乐");
    }
}
