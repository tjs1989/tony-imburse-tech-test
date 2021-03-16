package com.utils;

import org.apache.commons.lang3.RandomStringUtils;

public class ApiUtils {

    public String getRandomOrderReference(int stringLength, boolean useLetters, boolean useNumbers) {
        return RandomStringUtils.random(stringLength, useLetters, useNumbers);
    }
}
