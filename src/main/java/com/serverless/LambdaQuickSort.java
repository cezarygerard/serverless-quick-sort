package com.serverless;

import java.util.Arrays;

/**
 * Created by czarek on 23.08.17.
 */
public class LambdaQuickSort {

    public int[] sort(int[] input) {

        boolean sortDidSth = quickSortStep(input);

        if (sortDidSth) {

            int[] lowerPart = Arrays.copyOfRange(input, 0, pivotalIndex(input));
            int[] upperPart = Arrays.copyOfRange(input, pivotalIndex(input), input.length - 1);

            int[] lowerPartSorted = lambda().invokeRecursive(lowerPart);
            int[] upperPartSorted = lambda().invokeRecursive(upperPart);

            return concat(lowerPartSorted, upperPartSorted);
        }

        return input;
    }

    private int[] concat(int[] lower, int[] upper) {
        int lowerLength = lower.length;
        int upperLength = upper.length;

        int[] result = new int[lowerLength + upperLength];
        System.arraycopy(lower, 0, result, 0, lowerLength);
        System.arraycopy(upper, 0, result, lowerLength, upperLength);
        return result;
    }

    private int pivotalIndex(int[] input) {
        return (input.length - 1) / 2;
    }

    private boolean quickSortStep(int[] input) {
        int lowerIndex = 0;
        int upperIndex = input.length - 1;

        if (lowerIndex >= upperIndex) {
            return false;
        }

        int pivotalIndex = (lowerIndex + upperIndex) / 2;
        int pivotValue = input[pivotalIndex];

        int i = lowerIndex;
        int j = upperIndex;

        while (i < j) {

            while (input[i] < pivotValue) {
                i++;
            }

            while (input[j] > pivotValue) {
                j--;
            }

            swap(input, i, j);
            i++;
            j--;
        }
        return true;
    }

    private LambdaInvoker lambda() {
        return UglyApplicationContext.getInstance().getLambdaInvoker();
    }


//    //just the reference implementation:
//    void quickSort(int[] input, int lowerIndex, int upperIndex) {
//        if (lowerIndex == upperIndex) {
//            return;
//        }
//
//        int pivotalIndex = (lowerIndex + upperIndex) / 2;
//        int pivotValue = input[pivotalIndex];
//
//        int i = lowerIndex;
//        int j = upperIndex;
//
//        while (i < j) {
//
//            while (input[i] < pivotValue) {
//                i++;
//            }
//
//            while (input[j] > pivotValue) {
//                j--;
//            }
//
//            swap(input, i, j);
//            i++;
//            j--;
//        }
//
//        quickSort(input, lowerIndex, pivotalIndex);
//        quickSort(input, pivotalIndex + 1, upperIndex);
//    }

    private void swap(int[] input, int i, int j) {
        int temp = input[i];
        input[i] = input[j];
        input[j] = temp;
    }


}
