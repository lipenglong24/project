package com.lipenglong.java.arithmetic.sort;

import java.util.Arrays;

/**
 * 归并排序算法实现
 * <p/>
 *
 * @author petric
 * @since 1.0-SNAPSHOT
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] array = SortUtil.genArray(10);
        System.out.println(Arrays.toString(array));
        int[] temp = new int[array.length];
        mergeSort(array, 0, array.length - 1, temp);
        System.out.println(Arrays.toString(array));
    }

    private static void mergeSort(int[] array, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(array, left, mid, temp);
            mergeSort(array, mid + 1, right, temp);
            merge(array, left, mid, right, temp);
        }
    }

    private static void merge(int[] array, int left, int mid, int right, int[] temp) {
        int i = left;
        int j = mid + 1;
        int t = 0;
        while (i <= mid && j <= right) {
            if (array[i] <= array[j]) {
                temp[t++] = array[i++];
            } else {
                temp[t++] = array[j++];
            }
        }
        while (i <= mid) {
            temp[t++] = array[i++];
        }
        while (j <= right) {
            temp[t++] = array[j++];
        }
        t = 0;
        while (left <= right) {
            array[left++] = temp[t++];
        }
    }
}
