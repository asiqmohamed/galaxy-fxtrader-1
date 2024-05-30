package com.swapstech.galaxy.fxtrader.model;

import java.util.Arrays;
import java.util.Optional;

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
		Optional<PricingCurrencyType> option = Arrays.stream(PricingCurrencyType.values())
				.filter(val -> val.value == value).findFirst();
		return option.isPresent() ? option.get() : null;
	}
}
