package com.lipenglong.java.arithmetic.sort;

import java.util.Arrays;

/**
 * 插入排序算法实现
 * <p/>
 *
 * @author petric
 * @since 1.0-SNAPSHOT
 */
public class InsertionSort {
    public static void main(String[] args) {
        int[] array = SortUtil.genArray(10);
        System.out.println(Arrays.toString(array));
        insertionSort(array);
        System.out.println(Arrays.toString(array));
    }

    private static void insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int j = i;
            while (j > 0 && array[j] < array[j - 1]) {
                SortUtil.swap(array, j, j - 1);
                System.out.println(Arrays.toString(array));
                j--;
            }
        }
    }
}
