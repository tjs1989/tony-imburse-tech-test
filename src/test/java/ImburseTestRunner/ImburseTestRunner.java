package ImburseTestRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/",
        glue = "stepDefinitions",
        publish = false,
        plugin = {"pretty",
                "html:target/cucumberReports/mobile/html",
                "json:target/cucumberReports/mobile/json"
        })
public class ImburseTestRunner {
}
