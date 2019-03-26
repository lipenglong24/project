package com.lipenglong.java.arithmetic.sort;

import java.util.Arrays;

/**
 * 冒泡排序算法实现
 * <p/>
 *
 * @author petric
 * @since 1.0-SNAPSHOT
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] array = SortUtil.genArray(10);
        System.out.println(Arrays.toString(array));
        bubbleSort(array);
        System.out.println(Arrays.toString(array));
    }

    private static void bubbleSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            boolean exchange = false;
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    SortUtil.swap(array, j, j + 1);
                    exchange = true;
                }
            }
            if (!exchange) {
                break;
            }
        }

    }
}
