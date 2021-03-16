package stepDefinitions;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static com.common.constants.*;
import static com.utils.Utils.generateHmac;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.responseSpecification;

public class MyStepdefs {
    private String path = HMAC_ENDPOINT;

    @Given("I enter a message")
    public void iEnterAMessage(){
        RestAssured.baseURI = IMBURSE_SANDBOX_BASE_URL;
        String hmac = generateHmac(PUBLIC_AUTH_KEY, PRIVATE_AUTH_KEY);
        Response response = given()
                .contentType("application/json")
                .header("Authorization", "hmac " + hmac)
                .post(path)
                .then().extract().response();

        System.out.println(response.statusCode());
        System.out.println(response.getHeaders());
        System.out.println(response.getBody().asString());

    }

    @When("I run the message")
    public void iRunTheMessage() {
        System.out.println("hi");
    }


}
