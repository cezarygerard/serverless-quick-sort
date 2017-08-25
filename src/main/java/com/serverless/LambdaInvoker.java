package com.serverless;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by czarek on 25.08.17.
 */
public class LambdaInvoker {
    public int[] invokeRecursive(int[] part) {
        Map<String, Object> map = new HashMap<>();
        String argBody = ApiGatewayResponse.builder().setObjectBody(part).build().getBody();

        map.put("body", argBody);

        String body = new Handler().handleRequest(map, null).getBody();
        return UglyApplicationContext.getInstance().getArgumentParser().parseInput(body);
    }

}
