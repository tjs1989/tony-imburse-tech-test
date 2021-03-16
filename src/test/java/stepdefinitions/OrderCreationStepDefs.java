package stepdefinitions;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.utils.ApiUtils;
import com.utils.CountryInfoUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.common.Constants.ACCOUNT_ID;
import static com.common.Constants.AUTHORIZATION_HEADER_TYPE;
import static com.common.Constants.IMBURSE_SANDBOX_BASE_URL;
import static com.common.Constants.ORDER_ALREADY_EXISTS_ERROR_CODE;
import static com.common.Constants.ORDER_MANAGEMENT_ENDPOINT;
import static com.common.Constants.TENANT_ID;
import static com.common.Constants.X_ACCOUNT_ID_HEADER;
import static com.common.Constants.X_TENANT_ID_HEADER;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class OrderCreationStepDefs {

    public Response orderCreationResponse = null;

    public String orderReference = "";

    public Response getOrderCreationResponse(String requestBody) {
        return given()
                .header(X_ACCOUNT_ID_HEADER, ACCOUNT_ID)
                .header(X_TENANT_ID_HEADER, TENANT_ID)
                .header(AUTHORIZATION_HEADER_TYPE, Hooks.bearerToken)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post(ORDER_MANAGEMENT_ENDPOINT)
                .then().extract().response();
    }

    @Given("I have an order for {string} with a currency of {string}")
    public void iHaveAnOrderForWithACurrencyOf(String country, String currency) {
        CountryInfoUtils countryInfoUtils = new CountryInfoUtils();
        ApiUtils apiUtils = new ApiUtils();


        String orderReference = apiUtils.getRandomOrderReference(25, true, true);

        String orderRequest = "{\"orderRef\": \"" + orderReference + "\"}";

        RestAssured.baseURI = IMBURSE_SANDBOX_BASE_URL;
        Response response = getOrderCreationResponse(orderRequest);

        System.out.println(response.statusCode());
        System.out.println(response.headers());
        System.out.println(response.body().asString());

    }

    @Given("I have an order reference of {int} characters")
    public void iHaveAnOrderReferenceOfCharacters(int orderReferenceLength) {
        ApiUtils apiUtils = new ApiUtils();
        orderReference = apiUtils.getRandomOrderReference(orderReferenceLength, true, true);
    }

    @When("I create an order without instructions")
    public void iCreateAnOrderWithoutInstructions() {
        String orderRequest = "{\"orderRef\": \"" + orderReference + "\"}";
        RestAssured.baseURI = IMBURSE_SANDBOX_BASE_URL;
        orderCreationResponse = getOrderCreationResponse(orderRequest);
    }

    @Then("I receive the order creation code of {int}")
    public void iReceiveTheOrderCreationCodeOf(int expectedStatusCode) {
        assertThat(orderCreationResponse.statusCode()).isEqualTo(expectedStatusCode);
    }

    @And("I create an order without instructions using the same reference")
    public void iCreateAnOrderWithoutInstructionsUsingTheSameReference() {
        if (orderReference.equals("")){
            iHaveAnOrderReferenceOfCharacters(25);
        }
        iCreateAnOrderWithoutInstructions();
    }
}
