package com.serverless;

import java.util.Arrays;

/**
 * Created by czarek on 25.08.17.
 */
public class ArgumentParser {

    public int[] parseInput(Object inputArray) {
        if (inputArray instanceof String) {
            return parseString(((String) inputArray));
        }
        return (int[]) inputArray;
    }

    private int[] parseString(String inputArray) {
        if (inputArray.length() < 3) {
            return new int[0];
        }

        String substring = inputArray.substring(1, inputArray.length() - 1);
        String[] splitted = substring.split(",");

        try {
            return Arrays.stream(splitted)
                    .map(Integer::valueOf)
                    .mapToInt(Integer::intValue)
                    .toArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new int[0];


    }
}
