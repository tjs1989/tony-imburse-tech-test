package com.utils;

import com.common.CountryCodes;
import com.common.CurrencyCodes;

public class CountryInfoUtils {

    public String getCountryCode(String desiredCountry) {
        String countryCode = "";
        for (CountryCodes country : CountryCodes.values()) {
            if (country.name().equalsIgnoreCase(desiredCountry)) {
                countryCode = country.getCountryCode();
                break;
            }
        }
        return countryCode;
    }

    public String getCurrencyCode(String desiredCurrency) {
        String currencyCode = "";
        for (CurrencyCodes currency : CurrencyCodes.values()) {
            if (currency.name().equalsIgnoreCase(desiredCurrency)) {
                currencyCode = currency.getCurrencyCode();
                break;
            }
        }
        return currencyCode;
    }
}
