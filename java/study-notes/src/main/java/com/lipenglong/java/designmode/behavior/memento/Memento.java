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
public class Memento {
    int money;
    ArrayList<String> fruits;

    public Memento(int money) {
        this.money = money;
        fruits = new ArrayList<>();
    }

    public int getMoney() {
        return money;
    }

    void addFruit(String fruit) {
        fruits.add(fruit);
    }

    List<String> getFruits() {
        return (List<String>) fruits.clone();
    }
}
