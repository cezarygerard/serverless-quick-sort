package com.serverless;

/**
 * Sets up the application - much faster than spring in serverless
 */
public class UglyApplicationContext {

    private static UglyApplicationContext instance = new UglyApplicationContext();

    private ArgumentParser argumentParser = new ArgumentParser();

    private LambdaInvoker lambdaInvoker = new RemoteLambdaInvoker(argumentParser);

    private LocalLambdaInvoker localLambdaInvoker = new LocalLambdaInvoker(argumentParser);

    private LambdaQuickSort lambdaQuickSort = new LambdaQuickSort(lambdaInvoker);

    private LambdaQuickSort localQuickSort = new LambdaQuickSort(localLambdaInvoker);

    private Handler handler = new Handler();

    private String functionName;

    private String functionVersion;


    public static String getFunctionName() {
        return instance.functionName;
    }

    public static void setFunctionName(String functionName) {
        instance.functionName = functionName;
    }

    public static String getFunctionVersion() {
        return instance.functionVersion;
    }

    public static void setFunctionVersion(String functionVersion) {
        instance.functionVersion = functionVersion;
    }

    public static LambdaQuickSort getLambdaQuickSort() {
        return instance.lambdaQuickSort;
    }

    public static ArgumentParser getArgumentParser() {
        return instance.argumentParser;
    }

    public static Handler getHandler() {
        return instance.handler;
    }

    public static LambdaQuickSort getLocalQuickSort() {
        return instance.localQuickSort;
    }

}
