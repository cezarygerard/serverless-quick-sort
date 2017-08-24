package com.serverless;

import com.amazonaws.services.lambda.AWSLambdaClientBuilder;
import com.amazonaws.services.lambda.model.InvocationType;
import com.amazonaws.services.lambda.model.InvokeRequest;
import com.amazonaws.services.lambda.model.InvokeResult;
import com.amazonaws.services.lambda.runtime.Context;

/**
 * Created by czarek on 23.08.17.
 */
public class LambdaQuickSort {

    public String sort(Object inputArray, Context context) {
//        int[] parsedInput = parse(inputArray);
//
//        if(parsedInput.length == 1){
//            return "[" + parsedInput[0] + "]";
//        }
//
//        int pivotalValue = parsedInput[parsedInput.length/2];
//

        InvokeRequest invokeRequest = new InvokeRequest()
                .withFunctionName(context.getFunctionName())
                .withQualifier(context.getFunctionVersion())
                .withPayload("{ \"body\": " + inputArray + "}")
                .withInvocationType(InvocationType.Event);

        InvokeResult invokeResult = AWSLambdaClientBuilder.defaultClient().invoke(invokeRequest);
        return invokeResult.getPayload().toString();
    }

    //just the reference implementation:
    void quickSort(int[] input, int lowerIndex, int upperIndex) {
        if (lowerIndex == upperIndex) {
            return;
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

        quickSort(input, lowerIndex, pivotalIndex);
        quickSort(input, pivotalIndex + 1, upperIndex);


    }

    private void swap(int[] input, int i, int j) {
        int temp = input[i];
        input[i] = input[j];
        input[j] = temp;
    }


}
