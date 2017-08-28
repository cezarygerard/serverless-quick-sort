package com.serverless;

import java.util.HashMap;
import java.util.Map;

public class LocalLambdaInvoker implements LambdaInvoker {

    private ArgumentParser argumentParser;

    public LocalLambdaInvoker(ArgumentParser argumentParser) {
        this.argumentParser = argumentParser;
    }

    //just for test, as for now
    @Override
    public int[] invokeRecursive(int[] input) {
        Map<String, Object> map = new HashMap<>();
        String responseBody = ApiGatewayResponse.builder()
                .setObjectBody(input)
                .build()
                .getBody();

        map.put("body", responseBody);

        String body = UglyApplicationContext.getHandler().handleRequest(map, null).getBody();

        return argumentParser.parseInput(body);
    }

}
