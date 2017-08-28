package com.serverless

import spock.lang.Specification

class LocalLambdaInvokerIntegrationTest extends Specification {

    LocalLambdaInvoker invoker = new LocalLambdaInvoker(UglyApplicationContext.argumentParser)

    def "recursive local invokation works"() {
        given:
        int[] input = [423, 5467, 93, 23, 5, 1321]
        int[] sortedCopy = new int[input.length]
        System.arraycopy(input, 0, sortedCopy, 0, input.length);

        when:
        def result = invoker.invokeRecursive(input)
        Arrays.sort(sortedCopy);

        then:
        result == sortedCopy
    }
}
