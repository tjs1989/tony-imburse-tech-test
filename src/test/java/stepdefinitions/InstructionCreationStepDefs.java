package stepDefinitions;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static com.common.constants.*;
import static com.utils.Utils.generateHmac;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.responseSpecification;

public class InstructionCreationStepDefs {

//    TODO Update gradle file to split out version numbers into variables
//    TODO break the gradle test and main imports into separate files
//    TODO See if you can have a logic to get the tokens in a before hook, and don't get it again if the time hasn't expired
//    TODO Create a README
//    TODO Write the actual tests into feature files


    @Given("I have a valid token")
    public void iHaveAValidToken(){
        RestAssured.baseURI = IMBURSE_SANDBOX_BASE_URL;
        String hmac = generateHmac(PUBLIC_AUTH_KEY, PRIVATE_AUTH_KEY);
        Response response = given()
                .contentType("application/json")
                .header("Authorization", "hmac " + hmac)
                .post(HMAC_ENDPOINT)
                .then().extract().response();

        System.out.println(response.statusCode());
        System.out.println(response.getHeaders());
        System.out.println(response.getBody().asString());
        String token = response.jsonPath().get("accessToken");
        System.out.println(token);

    }

    @When("I run the message")
    public void iRunTheMessage() {
        System.out.println("hi");
    }


}
