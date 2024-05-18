package com.swapstech.galaxy.fxtrader.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PricingCurrencyType {

    FGN_CCY (1, "Foreign Currency"),
    BASE_CCY (2, "Base Currency"),
    QUOTE_CCY (3, "Quote Currency"),
    BOOK_CCY (4, "Book Currency");

    public int getValue() {
        return value;
    }

    private int value;
    private String description;

    PricingCurrencyType(int value, String description) {
        this.value = value;
        this.description = description;
    }

    @Override
    @JsonValue
    public String toString() {
        return String.valueOf(value);
    }

    @JsonCreator
    public static PricingCurrencyType fromValue(int value) {
        for (PricingCurrencyType b : PricingCurrencyType.values()) {
            if (String.valueOf(b.value).equals(value)) {
                return b;
            }
        }
        return null;
    }
}
