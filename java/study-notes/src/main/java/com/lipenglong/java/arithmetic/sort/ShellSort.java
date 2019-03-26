package com.lipenglong.java.arithmetic.sort;

import java.util.Arrays;

/**
 * 希尔排序算法实现
 * <p/>
 *
 * @author petric
 * @since 1.0-SNAPSHOT
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] array = new int[]{1, 0, 5, 12, 17, 10, 4, 9, 0, 8};
        System.out.println(Arrays.toString(array));
        shellSort2(array);
        System.out.println(Arrays.toString(array));
    }

    private static void shellSort(int[] array) {
        for (int gap = array.length / 2; gap > 0; gap /= 2) {
            System.out.println("gap = " + gap);
            for (int i = gap; i < array.length; i++) {
                int j = i;
                while (j - gap >= 0 && array[j] < array[j - gap]) {
                    SortUtil.swap(array, j, j - gap);
                    System.out.println("i=" + i + "; j=" + j + ". " + Arrays.toString(array));
                    j -= gap;
                }
            }
        }
    }

    private static void shellSort2(int[] array) {
        for (int gap = array.length / 2; gap > 0; gap /= 2) {
            System.out.println("gap=" + gap);
            for (int i = gap; i < array.length; i++) {
                int j = i;
                int temp = array[j];
                if (array[j] < array[j - gap]) {
                    while (j - gap >= 0 && temp < array[j - gap]) {
                        array[j] = array[j - gap];
                        System.out.println("i=" + i + "; j=" + j + ". " + Arrays.toString(array));
                        j -= gap;
                    }
                    array[j] = temp;
                }
            }
        }
    }
}
