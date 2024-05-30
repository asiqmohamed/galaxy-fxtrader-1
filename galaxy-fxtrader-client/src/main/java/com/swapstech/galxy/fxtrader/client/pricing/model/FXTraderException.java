package com.swapstech.galxy.fxtrader.client.pricing.model;

import org.springframework.validation.annotation.Validated;

/**
 * CustomException
 */
@Validated
public class FXTraderException extends RuntimeException {
    private Integer errorCode;
    private final String status = "ERROR";

    public FXTraderException(String message, Integer errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public String getStatus() {
        return status;
    }
}

