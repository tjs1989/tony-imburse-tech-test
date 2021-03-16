package com.common;

public enum CurrencyCodes {
    UNITED_KINGDOM("GB"),
    IRELAND("IE"),
    USA("US"),
    FRANCE("FR"),
    GERMANY("DE");

    private String countryCode;

    CurrencyCodes(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryCode() {
        return countryCode;
    }
}

