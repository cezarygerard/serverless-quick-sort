package com.serverless;

/**
 * Created by czarek on 25.08.17.
 */
public class UglyApplicationContext {

    private static UglyApplicationContext instance;

    private LambdaQuickSort lambdaQuickSort = new LambdaQuickSort();

    private ArgumentParser argumentParser = new ArgumentParser();

    private LambdaInvoker lambdaInvoker = new LambdaInvoker();
    ;

    private UglyApplicationContext() {
        init();
    }

    static UglyApplicationContext getInstance() {
        if (instance == null) {
            synchronized (UglyApplicationContext.class) {
                if (instance == null) {
                    instance = new UglyApplicationContext();
                }
            }
        }
        return instance;
    }

    private void init() {
        this.argumentParser = new ArgumentParser();
    }

    public LambdaQuickSort getLambdaQuickSort() {
        return lambdaQuickSort;
    }

    public ArgumentParser getArgumentParser() {
        return argumentParser;
    }

    public LambdaInvoker getLambdaInvoker() {
        return lambdaInvoker;
    }


}
