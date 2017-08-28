package com.serverless;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

/**
 * Created by czarek on 25.08.17.
 */
public class ArgumentParser {

    public int[] parseInput(Object inputArray) {
        if (inputArray instanceof String) {
            return parseString(((String) inputArray));
        }
        if (inputArray instanceof List) {
            return parseArrayList((List<Integer>) inputArray);

        }
        return (int[]) inputArray;
    }

    private int[] parseArrayList(List<Integer> inputArray) {
        int[] retVal = new int[inputArray.size()];
        for (int i = 0; i < inputArray.size(); i++) {
            retVal[i] = (inputArray.get(i)).intValue();
        }
        return retVal;
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

    public int[] parseByteBufferInput(ByteBuffer buffer) {
        byte[] bytes;
        if (buffer.hasArray()) {
            bytes = buffer.array();
        } else {
            bytes = new byte[buffer.remaining()];
            buffer.get(bytes);
        }
        return parseInput(new String(bytes, Charset.forName("UTF-8")));
    }
}
