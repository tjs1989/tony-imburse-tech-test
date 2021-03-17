package stepdefinitions;


import com.apiinteractions.ordersapi.OrdersApiInteractions;
import com.utils.ApiUtils;
import com.utils.CountryInfoUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static com.common.Constants.ACCOUNT_ID;
import static com.common.Constants.AUTHORIZATION_HEADER_TYPE;
import static com.common.Constants.IMBURSE_SANDBOX_BASE_URL;
import static com.common.Constants.ORDER_MANAGEMENT_ENDPOINT;
import static com.common.Constants.TENANT_ID;
import static com.common.Constants.X_ACCOUNT_ID_HEADER;
import static com.common.Constants.X_TENANT_ID_HEADER;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class OrderCreationStepDefs {

    public Response orderCreationResponse = null;

    public static String orderReference = "";

    public static Map<String, String> getRequestHeaders() {
        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put(X_ACCOUNT_ID_HEADER, ACCOUNT_ID);
        requestHeaders.put(X_TENANT_ID_HEADER, TENANT_ID);
        requestHeaders.put(AUTHORIZATION_HEADER_TYPE, Hooks.bearerToken);
        return requestHeaders;
    }

    public Response getOrderCreationResponse(String requestBody) {
        RestAssured.baseURI = IMBURSE_SANDBOX_BASE_URL;
        return given()
                .headers(getRequestHeaders())
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post(ORDER_MANAGEMENT_ENDPOINT)
                .then().extract().response();
    }

    @Given("I have an order reference of {int} characters")
    public void iHaveAnOrderReferenceOfCharacters(int orderReferenceLength) {
        ApiUtils apiUtils = new ApiUtils();
        orderReference = apiUtils.getRandomOrderReference(orderReferenceLength, true, true);
    }

    @When("I create an order without instructions")
    public void iCreateAnOrderWithoutInstructions() {
        String orderRequest = "{\"orderRef\": \"" + orderReference + "\"}";
        orderCreationResponse = getOrderCreationResponse(orderRequest);
    }

    @Then("I receive the order creation code of {int}")
    public void iReceiveTheOrderCreationCodeOf(int expectedStatusCode) {
        assertThat(orderCreationResponse.statusCode()).isEqualTo(expectedStatusCode);
    }

    @And("I create an order without instructions using the same reference")
    public void iCreateAnOrderWithoutInstructionsUsingTheSameReference() {
        ApiUtils apiUtils = new ApiUtils();
        if (orderReference.equals("")) {
            iHaveAnOrderReferenceOfCharacters(apiUtils.getRandomOrderReferenceLength());
        }
        iCreateAnOrderWithoutInstructions();
    }

    @And("I create an order with instructions for {string} with a currency of {string}")
    public void iCreateAnOrderWithInstructionsForWithACurrencyOf(String country, String currency) {
        OrdersApiInteractions ordersApiInteractions = new OrdersApiInteractions();
        CountryInfoUtils countryInfoUtils = new CountryInfoUtils();
        ApiUtils apiUtils = new ApiUtils();

        iHaveAnOrderReferenceOfCharacters(apiUtils.getRandomOrderReferenceLength());
        String countryCode = countryInfoUtils.getCountryCode(country);
        String currencyCode = countryInfoUtils.getCurrencyCode(currency);

        String orderRequestBody = ordersApiInteractions.getOrderRequestBodyWithInstructions(
                orderReference, countryCode, currencyCode);

        orderCreationResponse = getOrderCreationResponse(orderRequestBody);
    }
}
