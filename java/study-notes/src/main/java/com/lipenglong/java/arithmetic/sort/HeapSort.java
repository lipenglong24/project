package com.lipenglong.java.arithmetic.sort;

import java.util.Arrays;

/**
 * 堆排序算法实现
 * <p/>
 *
 * @author petric
 * @since 1.0-SNAPSHOT
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] array = SortUtil.genArray(10);
        System.out.println(Arrays.toString(array));
        heapSort(array);
        System.out.println(Arrays.toString(array));
    }

    private static void heapSort(int[] array) {
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            adjustHeap(array, i, array.length);
            System.out.println(Arrays.toString(array));
        }
        for (int j = array.length - 1; j > 0; j--) {
            SortUtil.swap(array, 0, j);
            adjustHeap(array, 0, j);
        }
    }

    private static void adjustHeap(int[] array, int i, int length) {
        int temp = array[i];
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            if (k + 1 < length && array[k] < array[k + 1]) {
                k++;
            }
            if (array[k] > temp) {
                array[i] = array[k];
                i = k;
            } else {
                break;
            }
        }
        array[i] = temp;
    }
}
