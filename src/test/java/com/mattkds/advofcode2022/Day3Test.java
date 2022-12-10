package com.mattkds.advofcode2022;
import com.mattkds.advofcode2022.day3.Day3;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class Day3Test {

    private final Day3 day3 = new Day3("/Users/matthieukydts/Documents/projects/fork/adventofcode2022/src/test/ressources/mattkds/day3/input-day3");

    @Test
    void shouldGetDay3Result() throws IOException {
        System.out.println("DAY 3 - Rucksack Reorganization\n==================================");
        System.out.println("Result first part : " + day3.getPrioritySumResultPartOne());
        System.out.println("Result second part : " + day3.getPrioritySecondPartProblem());
        System.out.println("==================================");
    }

}
