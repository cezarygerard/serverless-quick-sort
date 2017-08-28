package com.serverless;

import java.util.Arrays;

public class LambdaQuickSort {

    private LambdaInvoker lambda;

    public LambdaQuickSort(LambdaInvoker lambdaInvoker) {
        this.lambda = lambdaInvoker;
    }

    public int[] sort(int[] input) {

        if (anyThingToSort(input)) {

            input = Arrays.copyOfRange(input, 0, input.length);

            executeQuickSortStep(input);

            int[] lowerPart = getLowerHalfOf(input);
            int[] upperPart = getUpperHalfOf(input);

            int[] lowerPartSorted = lambda.invokeRecursive(lowerPart);
            int[] upperPartSorted = lambda.invokeRecursive(upperPart);

            return concat(lowerPartSorted, upperPartSorted);
        }

        return input;
    }

    private int[] getLowerHalfOf(int[] input) {
        return Arrays.copyOfRange(input, 0, pivotalIndex(input));
    }

    private int[] getUpperHalfOf(int[] input) {
        return Arrays.copyOfRange(input, pivotalIndex(input), input.length);
    }

    private boolean anyThingToSort(int[] input) {
        int lowerIndex = 0;
        int upperIndex = input.length - 1;

        return lowerIndex < upperIndex;
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
        return (input.length) / 2;
    }

    private boolean executeQuickSortStep(int[] input) {
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


    private void swap(int[] input, int i, int j) {
        int temp = input[i];
        input[i] = input[j];
        input[j] = temp;
    }


}
