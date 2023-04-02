package org.genthz.dsl.simple;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

public class NameGenerator {
    private static final Random RANDOM = new Random();

    private static final String[] PREFIX = {
            "alina",
            "catalina",
            "lina",
            "marina",
            "anya",
            "lola",
            "mila",
            "deva"
    };


    public static String next() {
        return new StringBuilder(PREFIX[RANDOM.nextInt(PREFIX.length)])
                .append(RandomStringUtils.randomAlphabetic(5))
                .toString();
    }
}
