package com.lipenglong.java.designmode.behavior.observer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * study-notes
 * <p/>
 *
 * @author petric
 * @since 1.0-SNAPSHOT
 */
public abstract class NumberGenerator {
    private List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void deleteObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        Iterator<Observer> it = observers.iterator();
        while (it.hasNext()) {
            it.next().update(this);
        }
    }

    public abstract int getNumber();

    public abstract void execute();
}
