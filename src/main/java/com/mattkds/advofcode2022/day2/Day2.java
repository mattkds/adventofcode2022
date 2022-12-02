package com.mattkds.advofcode2022.day2;
import com.mattkds.advofcode2022.utils.FileUtils;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Class to resolve the Day2 calendar problem
 */
public class Day2 {

    private static final int WIN_SCORE = 6;
    private static final int DRAW_SCORE = 3;
    private static final int LOSS_SCORE = 0;

    private static final char WIN_LETTER = 'Z';
    private static final char DRAW_LETTER = 'Y';
    private static final char LOSS_LETTER = 'X';

    public Day2(String filename) {
        this.filename = filename;
    }

    private String filename;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * Method to resolve the first part of the problem
     * In this situation we the opponent choice and our personal choice
     * For each round we calculate the game score following this choices
     * @return the global game score of the first strategy
     */
    public Integer retrieveGameScoreOpponentStrategy() {
        try {
            AtomicReference<Integer> globalScore = new AtomicReference<>(0);
            String fileContent = FileUtils.getFileContentAsString(this.getFilename());
            List<String> rounds = fileContent.lines().toList();
            rounds.forEach(round -> {
                List<String> roundTurn = List.of(round.split(" "));
                try {
                    globalScore.set(globalScore.get() + getScoreByRound(roundTurn.get(0).charAt(0), roundTurn.get(1).charAt(0)));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            });
            return globalScore.get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method to resolve the second part of the problem
     * In this situation we have the final result of a round game, and we have the shape played by the opponent
     * For each round we guess the shape to play to have the correct final result
     * For each round the score is also calculated following the choice made
     * @return the global game score of the second strategy
     */
    public Integer retrieveGameScoreSecondStrategyEndIndicates() {
        try {
            AtomicReference<Integer> globalScore = new AtomicReference<>(0);
            String fileContent = FileUtils.getFileContentAsString(this.getFilename());
            List<String> rounds = fileContent.lines().toList();
            rounds.forEach(round -> {
                List<String> roundTurn = List.of(round.split(" "));
                try {
                    globalScore.set(globalScore.get() + getSecondStrategyScore(roundTurn.get(0).charAt(0), roundTurn.get(1).charAt(0)));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            });
            return globalScore.get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method to get the socre from a round following the shape letter give by an opponent and my personal shape choice
     * @param opponentShapeLetter opponent choice for this round
     * @param personalShapeLetter personal choice for this round
     * @return the score of this round for us
     */
    private Integer getScoreByRound(char opponentShapeLetter, char personalShapeLetter) throws IllegalAccessException {
        Shape opponentChoice = Shape.valueOfLetterFromBasicOrPersonal(opponentShapeLetter);
        Shape personalChoice = Shape.valueOfLetterFromBasicOrPersonal(personalShapeLetter);
        int roundScore = 0;

        if (opponentChoice.equals(Shape.ROCK)) {
            switch (Objects.requireNonNull(personalChoice)) {
                case PAPER -> roundScore = WIN_SCORE;
                case ROCK -> roundScore = DRAW_SCORE;
                case SCISSORS -> roundScore = LOSS_SCORE;
            }
        } else if (opponentChoice.equals(Shape.PAPER)) {
            switch (Objects.requireNonNull(personalChoice)) {
                case PAPER -> roundScore = DRAW_SCORE;
                case ROCK -> roundScore = LOSS_SCORE;
                case SCISSORS -> roundScore = WIN_SCORE;
            }
        } else if (opponentChoice.equals(Shape.SCISSORS)) {
            switch (Objects.requireNonNull(personalChoice)) {
                case PAPER -> roundScore = LOSS_SCORE;
                case ROCK -> roundScore = WIN_SCORE;
                case SCISSORS -> roundScore = DRAW_SCORE;
            }
        }
        return roundScore + personalChoice.value;
    }

    private Integer getSecondStrategyScore(char opponentShapeLetter, char winLetter) throws IllegalAccessException {
        Shape opponentShape = Shape.valueOfLetter(opponentShapeLetter);
        int roundScore = 0;

        if (winLetter == WIN_LETTER) {
            switch (Objects.requireNonNull(opponentShape)) {
                case PAPER -> roundScore = Shape.SCISSORS.value + WIN_SCORE;
                case ROCK -> roundScore = Shape.PAPER.value + WIN_SCORE;
                case SCISSORS -> roundScore = Shape.ROCK.value + WIN_SCORE;
            }
        } else if (winLetter == DRAW_LETTER) {
            switch (Objects.requireNonNull(opponentShape)) {
                case PAPER -> roundScore = Shape.PAPER.value + DRAW_SCORE;
                case ROCK -> roundScore = Shape.ROCK.value + DRAW_SCORE;
                case SCISSORS -> roundScore = Shape.SCISSORS.value + DRAW_SCORE;
            }
        } else if (winLetter == LOSS_LETTER) {
            switch (Objects.requireNonNull(opponentShape)) {
                case PAPER -> roundScore = Shape.ROCK.value + LOSS_SCORE;
                case ROCK -> roundScore = Shape.SCISSORS.value + LOSS_SCORE;
                case SCISSORS -> roundScore = Shape.PAPER.value + LOSS_SCORE;
            }
        }
        return roundScore;
    }
}
