package com.lipenglong.java.designmode.create.builder;

/**
 * study-notes
 * <p/>
 *
 * @author petric
 * @since 1.0-SNAPSHOT
 */
class MealB extends MealBuilder {
    @Override
    void buildFood() {
        meal.setFood("蛋挞");
    }

    @Override
    void buildDrink() {
        meal.setDrink("柠檬果汁");
    }
}
