package imbursetestrunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/",
        glue = "stepdefinitions",
        publish = false,
        plugin = {"pretty",
                "html:target/imburseReports/html/imburseReport.html",
                "json:target/imburseReports/json/imburseReport.json"
        })
public class ImburseTestRunner {
}
