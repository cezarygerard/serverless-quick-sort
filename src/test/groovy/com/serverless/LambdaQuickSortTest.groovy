package com.serverless

import spock.lang.Specification

class LambdaQuickSortTest extends Specification {

    LocalLambdaInvoker noopLambdaInvoker = new LocalLambdaInvoker(UglyApplicationContext.instance.argumentParser) {
        int[] invokeRecursive(int[] input) {
            return input
        }
    }

    def "executes one step correctly"() {
        given:
        LambdaQuickSort lambdaQuickSort = new LambdaQuickSort(noopLambdaInvoker)
        int[] input = [4, 3, 1, 2, 9]

        when:
        int[] result = lambdaQuickSort.sort(input)

        then:
        result == [1, 3, 4, 2, 9]
    }

    def "executes next steps recursively"() {
        given:
        LocalLambdaInvoker mockedLambdaInvoker = Mock(LocalLambdaInvoker)
        LambdaQuickSort lambdaQuickSort = new LambdaQuickSort(mockedLambdaInvoker)
        int[] input = [4, 3, 1, 2, 9]

        when:
        lambdaQuickSort.sort(input)

        then:
        1 * mockedLambdaInvoker.invokeRecursive([1, 3]) >> []
        1 * mockedLambdaInvoker.invokeRecursive([4, 2, 9]) >> []
    }
}
