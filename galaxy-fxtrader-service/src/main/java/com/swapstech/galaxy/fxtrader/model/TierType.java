package com.swapstech.galaxy.fxtrader.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TierType {

    DEFAULT_SALES (1, "Default Sales"),
    DEFAULT_TRADING (2, "Default Trading"),
    SALES (3, "Sales Tier"),
    TRADING (4, "Trading Tier"),
    CHANNEL (5, "Channel Tier");

    public int getValue() {
        return value;
    }

    private int value;
    private String description;

    TierType(int value, String description) {
        this.value = value;
        this.description = description;
    }

    @Override
    @JsonValue
    public String toString() {
        return String.valueOf(value);
    }

    @JsonCreator
    public static TierType fromValue(int value) {
        for (TierType b : TierType.values()) {
            if (b.value == value) {
                return b;
            }
        }
        return null;
    }
}
