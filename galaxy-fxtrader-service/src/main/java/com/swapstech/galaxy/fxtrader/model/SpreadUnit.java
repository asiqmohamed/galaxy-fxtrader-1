package com.swapstech.galaxy.fxtrader.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;
import java.util.Optional;

public enum SpreadUnit {

    PIP (1, "Pips"),
    PERCENTAGE (2, "Percentage"),
    USD (3, "US Dollar");
	//DI (4, "Dealer Intervention");

    private int value;
    private String description;

    SpreadUnit(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    @Override
    @JsonValue
    public String toString() {
        return String.valueOf(value);
    }

    @JsonCreator
    public static SpreadUnit fromValue(int value) {
        Optional<SpreadUnit> option = Arrays.stream(SpreadUnit.values()).filter(val -> val.value == value).findFirst();
        return option.isPresent() ? option.get() : null;
    }
}
