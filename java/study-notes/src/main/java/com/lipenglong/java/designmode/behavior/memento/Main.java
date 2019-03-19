package com.lipenglong.java.designmode.behavior.memento;

/**
 * study-notes
 * <p/>
 *
 * @author petric
 * @since 1.0-SNAPSHOT
 */
public class Main {
    public static void main(String[] args) {
        Gamer gamer = new Gamer(100);
        Memento memento = gamer.createMemento();
        for (int i = 0; i < 10; i++) {
            System.out.println("========= " + i);
            System.out.println("当前状态： " + gamer);

            gamer.bet();

            System.out.println("所持有的金钱：" + gamer.getMoney());
            if (gamer.getMoney() > memento.getMoney()) {
                System.out.println("持有的金钱增加了，保持游戏状态");
                memento = gamer.createMemento();
            } else if (gamer.getMoney() < memento.getMoney() / 2) {
                System.out.println("持有的金钱减少了，恢复游戏状态！");
                gamer.restoreMemento(memento);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
