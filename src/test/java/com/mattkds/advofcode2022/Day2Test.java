package com.mattkds.advofcode2022;
import com.mattkds.advofcode2022.day2.Day2;
import org.junit.jupiter.api.Test;

public class Day2Test {

    private final Day2 day2 = new Day2("/Users/matthieukydts/Documents/projects/fork/adventofcode2022/src/test/ressources/mattkds/day2/input-day2");

    @Test
    void shouldGetDay2Result() {
        System.out.println("DAY 2 - ELF GAME\n==================================");
        System.out.println("The game score for the first strategy is : " + day2.retrieveGameScoreOpponentStrategy());
        System.out.println("The game score for the second strategy is : " + day2.retrieveGameScoreSecondStrategyEndIndicates());
        System.out.println("==================================");
    }

}
