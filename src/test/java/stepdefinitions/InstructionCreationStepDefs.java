package stepdefinitions;


import com.apiinteractions.instructionsapi.InstructionsApiInteractions;
import com.utils.CountryInfoUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static com.common.Constants.IMBURSE_SANDBOX_BASE_URL;
import static com.common.Constants.INSTRUCTION_ENDPOINT;
import static com.common.Constants.ORDER_MANAGEMENT_ENDPOINT;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static stepdefinitions.OrderCreationStepDefs.getRequestHeaders;
import static stepdefinitions.OrderCreationStepDefs.orderReference;

public class InstructionCreationStepDefs {

//    TODO Update gradle file to split out version numbers into variables
//    TODO break the gradle test and main imports into separate files
//    TODO Create a README

    InstructionsApiInteractions instructionsApiInteractions = new InstructionsApiInteractions();
    CountryInfoUtils countryInfoUtils = new CountryInfoUtils();

    private String instructionRequestBody = "";

    public Response instructionSubmissionResponse = null;

    public Response getInstructionResponse(String orderRef, String requestBody) {
        RestAssured.baseURI = IMBURSE_SANDBOX_BASE_URL;
        return given()
                .headers(getRequestHeaders())
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post(ORDER_MANAGEMENT_ENDPOINT + orderRef + INSTRUCTION_ENDPOINT)
                .then().extract().response();
    }

    @When("I submit the instruction")
    public void iSubmitTheInstruction() {
        instructionSubmissionResponse = getInstructionResponse(orderReference, instructionRequestBody);
    }

    @Then("I receive an instruction response code of {int}")
    public void iReceiveAnInstructionResponseCodeOf(int statusCode) {
        assertThat(instructionSubmissionResponse.statusCode()).isEqualTo(statusCode);
    }

    @Given("I create an Instruction for {string} with a currency of {string}")
    public void iCreateAnInstructionForWithACurrencyOf(String country, String currency) {
        String countryCode = countryInfoUtils.getCountryCode(country);
        String currencyCode = countryInfoUtils.getCurrencyCode(currency);
        instructionRequestBody = instructionsApiInteractions.getInstructionRequestBody(countryCode, currencyCode);
    }

    @And("I submit the instruction again")
    public void iSubmitTheInstructionAgain() {
        iSubmitTheInstruction();
    }
}
