package com.lipenglong.java.designmode.create.builder;

/**
 * 套餐构建者
 * <p/>
 *
 * @author petric
 * @since 1.0-SNAPSHOT
 */
abstract class MealBuilder {
    Meal meal = new Meal();

    abstract void buildFood();

    abstract void buildDrink();

    Meal getMeal() {
        return meal;
    }
}
