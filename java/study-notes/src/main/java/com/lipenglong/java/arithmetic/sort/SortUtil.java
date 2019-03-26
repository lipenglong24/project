package com.lipenglong.java.arithmetic.sort;

import java.util.Random;

/**
 * study-notes
 * <p/>
 *
 * @author petric
 * @since 1.0-SNAPSHOT
 */
class SortUtil {
    static int[] genArray(int length) {
        int[] array = new int[length];
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            array[i] = random.nextInt(length * 2);
        }
        return array;
    }

    static void swap(int[] array, int i, int j) {
        array[i] += array[j];
        array[j] = array[i] - array[j];
        array[i] -= array[j];
    }
}
