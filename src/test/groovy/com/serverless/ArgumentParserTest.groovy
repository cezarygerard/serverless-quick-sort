package com.serverless

import spock.lang.Specification

/**
 * Created by czarek on 25.08.17.
 */
class ArgumentParserTest extends Specification {

    ArgumentParser parser = new ArgumentParser()

    def "Parses int[] array"() {
        given:
        int[] arrayToParse = new int[5]

        when:
        int[] result = parser.parseInput(arrayToParse)

        then:
        result == arrayToParse
    }

    def "Parses String representation of json"() {
        given:
        String arrayToParse = '[1,5,3,23432,6]'

        when:
        int[] result = parser.parseInput(arrayToParse)

        then:
        result == [1, 5, 3, 23432, 6]
    }

    def "Parses empty String representation of json"() {
        given:
        String arrayToParse = '[]'

        when:
        def result = parser.parseInput(arrayToParse)

        then:
        result == new int[0]
    }

    def "Parses List<Integer>"() {
        given:
        List<Integer> input = [1, 5, 3, 23432, 6]

        when:
        def result = parser.parseInput(input)

        then:
        result == [1, 5, 3, 23432, 6] as int[]
    }
}
