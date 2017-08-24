package com.serverless
/**
 * Created by czarek on 24.08.17.
 */
class LambdaQuickSortTest extends spock.lang.Specification {

    def "Local QuickSort happy path works"() {
        given:
        int[] input = [423, 5467, 93, 23, 5, 1321]
        when:
        new LambdaQuickSort().quickSort(input, 0, input.length - 1)
        then:
        input == [5, 23, 93, 423, 5467, 1321]
    }
}
