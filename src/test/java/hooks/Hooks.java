package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utilities.ConfigReader;
import utilities.Driver;

public class Hooks {

    @Before(order=1,value="@url")
    public void navigateToHomePage(){
        Driver.getDriver().get(ConfigReader.getProperty("app_url"));
    }

    @After
    public void tearDown(Scenario scenario) {

        System.out.println("After Hooks");
        //adding reports that is generated when a scenrio fails
        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "failed scenerio");
            Driver.closeDriver();

        }
    }
}
