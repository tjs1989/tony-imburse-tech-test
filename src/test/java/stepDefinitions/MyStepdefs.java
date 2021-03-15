package MyStepDefs;

import demo.App;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class MyStepdefs {
    @Given("I enter a message")
    public void iEnterAMessage(){
        System.out.println("hello");
    }

    @When("I run the message")
    public void iRunTheMessage() {
        System.out.println(App.getGreeting());
    }


}
