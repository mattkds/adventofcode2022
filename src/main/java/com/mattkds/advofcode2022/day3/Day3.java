package com.mattkds.advofcode2022.day3;

import com.mattkds.advofcode2022.utils.FileUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class Day3 {

    private static final int FIRST_LOWERCASE_VALUE = 1;
    private static final int FIRST_UPPERCASE_VALUE = 27;

    public Day3(String filename) {
        this.filename = filename;
        this.defaultListItem = this.buildDefaultItemsAndPriorityValueList();
    }

    private String filename;

    public List<Item> getDefaultListItem() {
        return defaultListItem;
    }

    public void setDefaultListItem(List<Item> defaultListItem) {
        this.defaultListItem = defaultListItem;
    }

    private List<Item> defaultListItem;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Integer getPrioritySumResultPartOne() throws IOException {
        String fileContent = FileUtils.getFileContentAsString(this.getFilename());
        List<String> elfRuckSacksAsString = fileContent.lines().toList();
        List<RuckSack> rucksacks = new ArrayList<>();
        AtomicReference<Integer> identifier = new AtomicReference<>(1);
        elfRuckSacksAsString.forEach(lineRuckStack -> rucksacks.add(this.buildRucksackV2(lineRuckStack, identifier.getAndSet(identifier.get() + 1), 2)));
        List<Item> listCommonItemOfEachRuckSack = rucksacks.stream().map(ruckSack -> this.getCommonItemCompartmentFromRuckSackV2(ruckSack, 2)).toList();
        return listCommonItemOfEachRuckSack.stream().mapToInt(Item::priority).sum();
    }

    public Integer getPrioritySecondPartProblem() throws IOException {
        String fileContent = FileUtils.getFileContentAsString(this.getFilename());
        List<String> elfRuckSacks = fileContent.lines().toList();
        LinkedList<RuckSack> rucksacks = new LinkedList<>();
        AtomicReference<Integer> identifier = new AtomicReference<>(1);
        elfRuckSacks.forEach(lineRuckStack -> rucksacks.add(this.buildRucksackV2(lineRuckStack, identifier.getAndSet(identifier.get() + 1), 1)));
        List<List<RuckSack>> groupingRuckSacksList = this.groupRuckSackByPartition(rucksacks, 3);
        List<Item> commonsItems = new ArrayList<>();

        for (List<RuckSack> partitionRuckSack: groupingRuckSacksList) {
            commonsItems.add(this.getCommonItemFromRuckSackGroup(partitionRuckSack));
        }
        return commonsItems.stream().mapToInt(Item::priority).sum();
    }

    private RuckSack buildRucksackV2(String line, Integer identifier, Integer numberOfCompartment) {
        RuckSack ruckSack = new RuckSack(identifier);
        int indexBegin = 0;
        int indexStep = line.length() / numberOfCompartment;
        int indexEnd = indexStep;
        int compartmentNumber = 0;
        for (int compartment = 1; compartment <= numberOfCompartment; compartment++) {
            String itemsCompartmentAsString = line.substring(indexBegin, indexEnd);
            ruckSack.getCompartmentsList().add(new ArrayList<>());
            int finalCompartmentNumber = compartmentNumber;
            itemsCompartmentAsString.chars().forEach(itemAsChar -> {
                ruckSack.getCompartmentsList().get(finalCompartmentNumber).add(this.defaultListItem.stream().filter(item -> item.name() == itemAsChar).findFirst().orElse(new Item('-', 0)));
            });
            indexEnd = indexEnd + indexStep;
            indexBegin = indexStep;
            compartmentNumber++;
        }
        return ruckSack;
    }

    private List<Item> buildDefaultItemsAndPriorityValueList() {
        Integer lowerCaseValue = FIRST_LOWERCASE_VALUE;
        Integer upperCaseValue = FIRST_UPPERCASE_VALUE;
        List<Item> defaultList = new ArrayList<>();
        for(char letter = 'a'; letter <= 'z'; letter++) {
            defaultList.add(new Item(letter, lowerCaseValue));
            defaultList.add(new Item(Character.toUpperCase(letter), upperCaseValue));
            lowerCaseValue++;
            upperCaseValue++;
        }
        return defaultList;
    }

    private Item getCommonItemCompartmentFromRuckSackV2(RuckSack ruckSack, Integer numberOfCompartment) {
        int compartmentIndex = 0;
        List<Item> commonItem = new ArrayList<>(ruckSack.getCompartmentsList().get(compartmentIndex));
        for (int compartmentIndexLoop = 1; compartmentIndexLoop < numberOfCompartment; compartmentIndexLoop++) {
            commonItem.retainAll(ruckSack.getCompartmentsList().get(compartmentIndexLoop));
        }
        return commonItem.stream().findFirst().orElse(new Item('-', 0));
    }

    private List<List<RuckSack>> groupRuckSackByPartition(LinkedList<RuckSack> ruckSackList, Integer partitionStep) {
        List<List<RuckSack>> ruckSacksGroupByPartition = new ArrayList<>();
        Integer listSize = ruckSackList.size();
        int partitionSize = listSize / partitionStep;
        int partitionStart = 0;
        int partitionEnd = partitionStep;
        for (int i = 0; i < partitionSize; i++) {
            ruckSacksGroupByPartition.add(ruckSackList.subList(partitionStart, partitionEnd));
            partitionEnd = partitionEnd + partitionStep;
            partitionStart = partitionStart + partitionStep;
        }
        return ruckSacksGroupByPartition;
    }

    private Item getCommonItemFromRuckSackGroup(List<RuckSack> ruckSacks) {
        List<Item> commonItem = new ArrayList<>(ruckSacks.get(0).getCompartmentsList().get(0));
        for (RuckSack ruckSack : ruckSacks) {
            commonItem.retainAll(ruckSack.getCompartmentsList().get(0));
        }
        return commonItem.stream().findFirst().orElse(new Item('-', 0));
    }
}
