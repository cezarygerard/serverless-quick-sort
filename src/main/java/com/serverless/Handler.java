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


        input.keySet().forEach(key -> LOG.info(
                "received: " + key
                        + "  val: " + input.get(key)
                        + "  type: " + Optional.ofNullable(input.get(key))
                        .map(it -> it.getClass().toString())
                        .orElse("received NOTHING!!!")));

        Object body = input.get("body");

        LOG.info("Received body: " + body);
        Optional.ofNullable(body).ifPresent(bdy ->
                LOG.info("Received body of class: " + bdy.getClass())
        );

        int[] parsedBody = UglyApplicationContext.getArgumentParser().parseInput(body);


        setCotext(context);

        LambdaQuickSort lambdaQuickSort = selectQuickSortAlborithm(context);

        int[] result = lambdaQuickSort.sort(parsedBody);

        return buildResponse(result);

    }

    private LambdaQuickSort selectQuickSortAlborithm(Context context) {
        return Optional.ofNullable(context)
                .map(ctx -> {
                    return UglyApplicationContext.getLambdaQuickSort();
                }).orElse(UglyApplicationContext.getLocalQuickSort());
    }

    private void setCotext(Context context) {
        Optional.ofNullable(context).ifPresent(ctx -> {
                    UglyApplicationContext.setFunctionName(ctx.getFunctionName());
                    UglyApplicationContext.setFunctionVersion(ctx.getFunctionVersion());
                }
        );
    }

    private ApiGatewayResponse buildResponse(int[] result) {
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
