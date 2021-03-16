package com.common;

public enum CurrencyCodes {
    BRITISH_POUND("GBP"),
    EURO("EUR"),
    US_DOLLAR("USD");

    private String currencyCode;

    CurrencyCodes(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }
}

