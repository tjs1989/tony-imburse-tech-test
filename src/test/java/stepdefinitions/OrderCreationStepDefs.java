package stepdefinitions;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class OrderCreationStepDefs {

//    TODO Update gradle file to split out version numbers into variables
//    TODO break the gradle test and main imports into separate files
//    TODO See if you can have a logic to get the tokens in a before hook, and don't get it again if the time hasn't expired
//    TODO Create a README
//    TODO Write the actual tests into feature files


    @Given("I have a valid token")
    public void iHaveAValidToken() {
        System.out.println("hello");
    }

    @When("I run the message")
    public void iRunTheMessage() {
        System.out.println("token" + Hooks.token);
    }


}
