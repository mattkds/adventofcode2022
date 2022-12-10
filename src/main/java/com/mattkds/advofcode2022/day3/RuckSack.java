package com.mattkds.advofcode2022.day3;

import java.util.LinkedList;
import java.util.List;

public class RuckSack {

    private final Integer identifier;

    LinkedList<List<Item>> compartmentsList;

    public List<List<Item>> getCompartmentsList() {
        return compartmentsList;
    }

    public RuckSack(Integer identifier) {
        this.identifier = identifier;
        this.compartmentsList = new LinkedList<>();
    }

    @Override
    public String toString() {
        return "RuckSack{" +
                "identifier=" + identifier +
                ", compartmentsList=" + compartmentsList +
                '}';
    }
}
