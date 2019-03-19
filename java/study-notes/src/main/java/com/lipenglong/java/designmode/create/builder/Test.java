package com.lipenglong.java.designmode.create.builder;

/**
 * study-notes
 * <p/>
 *
 * @author petric
 * @since 1.0-SNAPSHOT
 */
class Test {
    public static void main(String[] args) {
        MealA mealA = new MealA();
        KFCWaiter waiter = new KFCWaiter(mealA);
        Meal meal = waiter.construct();
        System.out.println("套餐A组成部分：");
        System.out.println("食物：" + meal.getFood() + "; 饮品：" + meal.getDrink());

        MealB mealB = new MealB();
        waiter = new KFCWaiter(mealB);
        meal = waiter.construct();
        System.out.println("套餐B组成部分：");
        System.out.println("食物：" + meal.getFood() + "; 饮品：" + meal.getDrink());
    }
}
