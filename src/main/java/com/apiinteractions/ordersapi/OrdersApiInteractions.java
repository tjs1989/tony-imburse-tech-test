package com.apiinteractions.ordersapi;

import com.utils.ApiUtils;

import static com.common.Constants.DEBIT_DIRECTION;
import static com.common.Constants.SCHEME_ID;

public class OrdersApiInteractions {
    ApiUtils apiUtils = new ApiUtils();

    public String getOrderRequestBodyWithInstructions(String orderRef, String country, String currency) {

        return "{" +
                "\"orderRef\": \"" + orderRef + "\"," +
                "\"instructions\": [{" +
                "\"instructionRef\": \"" + apiUtils.getUuid() + "\"," +
                "\"customerRef\": \"" + apiUtils.getUuid() + "\"," +
                "\"direction\": \"" + DEBIT_DIRECTION + "\"," +
                "\"amount\": \"" + apiUtils.getRandomOrderAmount() + "\"," +
                "\"currency\": \"" + currency + "\"," +
                "\"country\": \"" + country + "\"," +
                "\"settledByDate\": \"" + apiUtils.getCurrentDate() + "\"," +
                "\"schemeId\": \"" + SCHEME_ID + "\"" +
                "}]}";

    }
}
