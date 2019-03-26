package com.lipenglong.java.designmode.behavior.memento;

import java.util.ArrayList;
import java.util.List;

/**
 * study-notes
 * <p/>
 *
 * @author petric
 * @since 1.0-SNAPSHOT
 */
class Memento {
    int money;
    ArrayList<String> fruits;

    Memento(int money) {
        this.money = money;
        fruits = new ArrayList<>();
    }

    int getMoney() {
        return money;
    }

    void addFruit(String fruit) {
        fruits.add(fruit);
    }

    List<String> getFruits() {
        return (List<String>) fruits.clone();
    }
}
