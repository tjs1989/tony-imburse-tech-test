package stepdefinitions;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class InstructionCreationStepDefs {

//    TODO Update gradle file to split out version numbers into variables
//    TODO break the gradle test and main imports into separate files
//    TODO Create a README
//    TODO Write the actual tests into feature files
//    TODO Create the order call to get an order reference back
//    TODO Create the instruction once the order has been created

    Hooks hooks = new Hooks();


    @Given("I have a valid token")
    public void iHaveAValidToken() {
        System.out.println("hello");
    }

    @When("I run the message")
    public void iRunTheMessage() {
        System.out.println("token " + Hooks.bearerToken);
    }
}
