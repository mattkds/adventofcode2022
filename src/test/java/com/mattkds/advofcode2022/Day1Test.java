package com.mattkds.advofcode2022;
import com.mattkds.advofcode2022.day1.Day1;
import org.junit.jupiter.api.Test;

public class Day1Test {

    private final Day1 day1 = new Day1("/Users/matthieukydts/Documents/projects/fork/adventofcode2022/src/test/ressources/mattkds/day1/input-day1");

    @Test
    void shouldGetDay1Result() {
        System.out.println("DAY 1 - ELF CALORIES\n==================================");
        System.out.println("The top elf calories is : " + day1.retrieveMostCaloriesElf(1));
        System.out.println("The sum of the top 3 elf calories is : " + day1.retrieveMostCaloriesElf(3));
        System.out.println("==================================");
    }

}
