package com.apiinteractions.instructionsapi;

import com.utils.ApiUtils;

import static com.common.Constants.DEBIT_DIRECTION;
import static com.common.Constants.SCHEME_ID;

public class InstructionsApiInteractions {
    ApiUtils apiUtils = new ApiUtils();

    public String getInstructionRequestBody(String country, String currency){
        return "{" +
                "\"instructionRef\": \"" + apiUtils.getUuid() + "\"," +
                "\"customerRef\": \"" + apiUtils.getUuid() + "\"," +
                "\"direction\": \"" + DEBIT_DIRECTION + "\"," +
                "\"amount\": \"" + apiUtils.getRandomOrderAmount() + "\"," +
                "\"currency\": \"" + currency + "\"," +
                "\"country\": \"" + country + "\"," +
                "\"settledByDate\": \"" + apiUtils.getCurrentDate() + "\"," +
                "\"schemeId\": \"" + SCHEME_ID + "\"" +
                "}";
    }
}
