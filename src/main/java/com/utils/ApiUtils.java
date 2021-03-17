package com.utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class ApiUtils {

    public String getCurrentDate(){
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(new Date());
    }

    public String getRandomOrderReference(int stringLength, boolean useLetters, boolean useNumbers) {
        return RandomStringUtils.random(stringLength, useLetters, useNumbers);
    }

    public String getRandomOrderAmount(){
        return String.format("%.2f", ThreadLocalRandom.current().nextDouble(0.01, 999999));
    }

    public String getUuid(){
        return UUID.randomUUID().toString();
    }

    public Integer getRandomOrderReferenceLength(){
       return ThreadLocalRandom.current().nextInt(1, 49 + 1);
    }


}

