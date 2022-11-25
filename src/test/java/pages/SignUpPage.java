package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class SignUpPage {

   public SignUpPage(){
        PageFactory.initElements(Driver.getDriver(),this);

    }
    @FindBy(name = "gender")
    public WebElement genderButtonMale;

   @FindBy(xpath = "(//*[@name='gender'])[2]")
    public WebElement genderButtonFemale;

   @FindBy(name = "firstName")
    public WebElement fistNameBox;

   @FindBy(name = "lastName")
    public WebElement lastNameBox;

   @FindBy(xpath = "//*[.='Email address']")
    public WebElement emailBox;

   @FindBy(name = "password")
    public WebElement passwordBox;

   @FindBy(xpath = "//*[.='Password confirmation']")
    public WebElement passwordConfirmationBox;

   @FindBy(xpath = "//*[@class='checkMark']")
    public WebElement generalTermsAndConditionsBox;

   @FindBy(xpath = "(//*[@class='checkMark'])[2]")
    public WebElement receiveNewOfferBox;

    @FindBy(xpath = "(//*[@class='checkMark'])[3]")
    public WebElement receiveLatestNewBox;

    @FindBy(xpath = "//*[@type='submit']")
    public  WebElement createYourAccountButton;




}
