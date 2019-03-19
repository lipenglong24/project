package com.lipenglong.java.designmode.create.builder;

/**
 * 套餐对象
 * <p/>
 *
 * @author petric
 * @since 1.0-SNAPSHOT
 */
class Meal {
    private String food;
    private String drink;

    String getFood() {
        return food;
    }

    void setFood(String food) {
        this.food = food;
    }

    String getDrink() {
        return drink;
    }

    void setDrink(String drink) {
        this.drink = drink;
    }
}
