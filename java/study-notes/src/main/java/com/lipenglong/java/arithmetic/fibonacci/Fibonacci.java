package com.lipenglong.java.arithmetic.fibonacci;

/**
 * study-notes
 * <p/>
 *
 * @author petric
 * @since 1.0-SNAPSHOT
 */
public class Fibonacci {
    public static void main(String[] args) {
        int n = 40;
        long begin = System.currentTimeMillis();
        int number1 = fibonacciRecursion(n);
        long end = System.currentTimeMillis();
        int number2 = fibonacciIterator(n);
        long end2 = System.currentTimeMillis();
        System.out.println(number1);
        System.out.println(number2);
        System.out.println("递归执行时间：" + (end - begin));
        System.out.println("迭代执行时间：" + (end2 - end));
    }

    private static int fibonacciIterator(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        int first = 1, second = 1, third = 0;
        for (int i = 3; i <= n; i++) {
            third = first + second;
            first = second;
            second = third;
        }
        return third;
    }

    private static int fibonacciRecursion(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        return fibonacciRecursion(n - 2) + fibonacciRecursion(n - 1);
    }
}
