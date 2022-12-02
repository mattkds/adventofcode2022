package com.mattkds.advofcode2022.day2;

import java.util.Arrays;

/**
 * Basic enum which represent the second strategy guide
 * Rock Paper & Sissors game has 3 differents shape with a specific value for each One
 */
public enum Shape {
    SCISSORS('C', 'Z', 3),
    PAPER('B', 'Y', 2),
    ROCK('A', 'X', 1);

    public final char basicShapeLetter;
    public final char personalShapeLetter;
    public final Integer value;

    Shape(char shapeLetter, char personnalShapeLetter, Integer value) {
        this.basicShapeLetter = shapeLetter;
        this.personalShapeLetter = personnalShapeLetter;
        this.value = value;
    }

    public static Shape valueOfLetter(char letter) throws IllegalAccessException {
        return Arrays.stream(Shape.values()).filter(shape -> shape.basicShapeLetter == letter).findFirst().orElseThrow(IllegalAccessException::new);
    }

    public static Shape valueOfLetterFromBasicOrPersonal(char letter) throws IllegalAccessException {
        return Arrays.stream(Shape.values()).filter(shape -> shape.basicShapeLetter == letter || shape.personalShapeLetter == letter).findFirst().orElseThrow(IllegalAccessException::new);
    }
}

