package com.lipenglong.java.arithmetic.sort;

import java.util.Arrays;

/**
 * 简单排序算法实现
 * <p/>
 *
 * @author petric
 * @since 1.0-SNAPSHOT
 */
public class SimpleSort {
    public static void main(String[] args) {
        int[] array = SortUtil.genArray(10);
        System.out.println(Arrays.toString(array));
        simpleSort(array);
        System.out.println(Arrays.toString(array));
    }

    private static void simpleSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int min = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[min] > array[j]) {
                    min = j;
                }
            }
            if (min != i) {
                SortUtil.swap(array, i, min);
            }
        }
    }
}
