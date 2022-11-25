package pages;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class LoginPage {

   public LoginPage(){
       PageFactory.initElements(Driver.getDriver(),this);
    }

   @FindBy(id = "onetrust-accept-btn-handler")
    public WebElement acceptCookies;
    @FindBy(xpath = "//*[.='Continue']")
    public WebElement continueButton;

}
