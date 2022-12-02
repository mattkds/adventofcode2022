package com.mattkds.advofcode2022.day1;

import com.mattkds.advofcode2022.utils.FileUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Class to resolve the Day1 calendar problem
 */
public class Day1 {

    private final List<ElfCalories> elfCaloriesList;
    private final String filename;
    public String getFilename() {
        return filename;
    }

    public Day1(String filename) {
        this.filename = filename;
        this.elfCaloriesList = new ArrayList<>();
    }

    public Integer retrieveMostCaloriesElf(int topElfLimit) {
        this.elfCaloriesList.clear();
        try {
            String fileContent = FileUtils.getFileContentAsString(this.getFilename());
            List<String> listOfCaloriesByElfAsString = List.of(fileContent.split("\\n\\n"));
            AtomicReference<Integer> elfId = new AtomicReference<>(0);
            listOfCaloriesByElfAsString.forEach(line -> {
                ElfCalories elfCalories = new ElfCalories(elfId.getAndSet(elfId.get() + 1), line.lines().mapToInt(Integer::parseInt).sum());
                this.elfCaloriesList.add(elfCalories);
            });
            List<ElfCalories> topElfCalories = elfCaloriesList.stream()
                    .sorted(Comparator.comparingInt(ElfCalories::TotalCalories)
                            .reversed())
                    .toList();
            return topElfCalories.stream()
                    .limit(topElfLimit)
                    .mapToInt(ElfCalories::TotalCalories)
                    .sum();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
