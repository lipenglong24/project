package com.lipenglong.java.designmode.behavior.strategy;

/**
 * study-notes
 * <p/>
 *
 * @author petric
 * @since 1.0-SNAPSHOT
 */
public class Main {
    public static void main(String[] args) {
        Player player1 = new Player("张三", new WinningStrategy(100));
        Player player2 = new Player("李四", new ProbStrategy(10));

        for (int i = 0; i < 20; i++) {
            Hand nextHand1 = player1.nextHand();
            Hand nextHand2 = player2.nextHand();
            if (nextHand1.isStrongerThan(nextHand2)) {
                System.out.println("winner: " + player1);
                player1.win();
                player2.lose();
            } else if (nextHand2.isStrongerThan(nextHand1)) {
                System.out.println("winner: " + player2);
                player2.win();
                player1.lose();
            } else {
                System.out.println("even...");
                player1.even();
                player2.even();
            }
        }

        System.out.println("total result: ");
        System.out.println(player1.toString());
        System.out.println(player2.toString());
    }
}
