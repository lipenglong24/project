package com.lipenglong.java.arithmetic.sort;

import java.util.Arrays;

/**
 * 快速排序算法实现
 * <p/>
 *
 * @author petric
 * @since 1.0-SNAPSHOT
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] array = SortUtil.genArray(20);
        System.out.println(Arrays.toString(array));
        int[] a = array.clone();
        int[] b = array.clone();
        quickSort(a, 0, array.length - 1);
        quickSort2(b, 0, array.length - 1);
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(b));
    }

    private static void quickSort(int[] array, int left, int right) {
        if (left < right) {
            int i = left;
            int j = right;
            int x = array[left];
            while (i < j) {
                while (i < j && array[j] >= x) {
                    j--;
                }
                if (i < j) {
                    array[i++] = array[j];
                }
                while (i < j && array[i] < x) {
                    i++;
                }
                if (i < j) {
                    array[j--] = array[i];
                }
            }
            array[i] = x;
            quickSort(array, left, i - 1);
            quickSort(array, i + 1, right);
        }
    }

    private static void quickSort2(int[] array, int left, int right) {
        if (left < right) {
            int i = left;
            int j = right;
            int x = array[left];
            while (i < j) {
                while (i < j && array[j] >= x) {
                    j--;
                }
                while (i < j && array[i] <= x) {
                    i++;
                }
                if (i < j) {
                    SortUtil.swap(array, i, j);
                }
            }
            array[left] = array[i];
            array[i] = x;
            quickSort2(array, left, i - 1);
            quickSort2(array, i + 1, right);
        }
    }
}
