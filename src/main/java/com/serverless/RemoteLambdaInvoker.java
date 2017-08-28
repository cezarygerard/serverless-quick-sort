package com.serverless;

import com.amazonaws.services.lambda.AWSLambdaClientBuilder;
import com.amazonaws.services.lambda.model.InvocationType;
import com.amazonaws.services.lambda.model.InvokeRequest;
import com.amazonaws.services.lambda.model.InvokeResult;

public class RemoteLambdaInvoker implements LambdaInvoker {

    private ArgumentParser argumentParser;

    public RemoteLambdaInvoker(ArgumentParser argumentParser) {
        this.argumentParser = argumentParser;
    }

    @Override
    public int[] invokeRecursive(int[] input) {
        InvokeRequest invokeRequest = buildInvokeRequest(input);
        InvokeResult invokeResult = AWSLambdaClientBuilder.defaultClient().invoke(invokeRequest);
        int[] retVal = argumentParser.parseByteBufferInput(invokeResult.getPayload());
        return retVal;
    }

    private InvokeRequest buildInvokeRequest(int[] input) {
        InvokeRequest invokeRequest = new InvokeRequest();
        invokeRequest.withPayload(getPayload(input));
        invokeRequest.withInvocationType(InvocationType.Event);
        invokeRequest.withQualifier(UglyApplicationContext.getFunctionVersion());
        invokeRequest.withFunctionName(UglyApplicationContext.getFunctionName());
        return invokeRequest;
    }

    private String getPayload(int[] input) {
        return "{ \"body\" : " + ApiGatewayResponse.builder()
                .setObjectBody(input)
                .build()
                .getBody() + "}";
    }
}

