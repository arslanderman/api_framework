package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        monochrome = true,
        plugin = {
                "pretty",
                "html:target/html-reports/cucember.html",
                "json:target/json-reports/cucember.json",
                "junit:target/xml-reports/cucember.xml",
                "rerun:target/failedRerun.txt",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        },

        features = "./src/test/resources/features/api_automation_class",
        glue = {"stepdefinitions/api_automation_step_defs","hooks"},
        tags = "@deneme",
        dryRun = false


)

public class Runner {
}
