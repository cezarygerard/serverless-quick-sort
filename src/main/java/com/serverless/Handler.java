package com.serverless;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Handler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {

    private static final Logger LOG = Logger.getLogger(Handler.class);

    @Override
    public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {
//		LOG.info("received: " + input);
        input.keySet().forEach(key -> LOG.info(
                "received: " + key
                        + "  val: " + input.get(key)
                        + "  type: " + Optional.ofNullable(input.get(key))
                        .map(it -> it.getClass().toString())
                        .orElse(" ")));

        Object body = (String) input.get("body");

        String result = new LambdaQuickSort().sort(body, context);

        Map<String, String> headers = new HashMap<>();
        headers.put("X-Powered-By", "AWS Lambda & Serverless");
        headers.put("Content-Type", "application/json");
        return ApiGatewayResponse.builder()
                .setStatusCode(200)
                .setObjectBody(result)
                .setHeaders(headers)
                .build();
    }
}
