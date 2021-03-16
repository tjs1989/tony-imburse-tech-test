package com.common;

public enum CountryCodes {
    UNITED_KINGDOM("GB"),
    IRELAND("IE"),
    USA("US"),
    FRANCE("FR"),
    GERMANY("DE");

    private String countryCode;

    CountryCodes(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryCode() {
        return countryCode;
    }
}

