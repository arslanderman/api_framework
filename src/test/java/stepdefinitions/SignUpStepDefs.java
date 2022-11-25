package stepdefinitions;

import com.github.javafaker.Faker;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.interactions.Actions;
import pages.Homepage;
import pages.LoginPage;
import pages.SignUpPage;
import pages.UserAccountPage;
import pojo.SignUpPojo;
import utilities.Driver;
import utilities.JSUtils;
import utilities.ReusableMethods;
import utilities.TxtWriter;

public class SignUpStepDefs {
    Homepage homepage;
    LoginPage loginPage;
    SignUpPage signUpPage;
    UserAccountPage userAccountPage;
    SignUpPojo signUpPojo = new SignUpPojo();
    Faker faker = new Faker();

    @Given("user is on the sign up page")
    public void user_is_on_the_sign_up_page() {
        homepage = new Homepage();
        homepage.logInButton.click();
        //ReusableMethods.waitForClickablility(loginPage.acceptCookies,5);
        loginPage = new LoginPage();
        ReusableMethods.waitFor(1);
      ReusableMethods.waitForClickablility(loginPage.acceptCookies,5);
        JSUtils.clickElementByJS(loginPage.acceptCookies);
        ReusableMethods.waitForVisibility(loginPage.continueButton,5);
        JSUtils.clickElementByJS(loginPage.continueButton);

    }
    @Given("user selects the gender")
    public void user_selects_the_gender() {
        signUpPage = new SignUpPage();
       // signUpPage.genderButtonFemale.click();
        JSUtils.clickElementByJS(signUpPage.genderButtonFemale);

    }
    @Then("user enters firstname and lastname as {string} and {string}")
    public void user_enters_firstname_and_lastname_as_and(String firstname, String lastname) {
        firstname = faker.name().firstName();
        lastname = faker.name().lastName();
        signUpPojo.setFirstname(firstname);
        signUpPojo.setLastname(lastname);
        signUpPage.fistNameBox.sendKeys(firstname);
        signUpPage.lastNameBox.sendKeys(lastname);
        ReusableMethods.waitFor(1);



    }
    @Then("user provides email as {string}")
    public void user_provides_email_as(String email) {

        email = signUpPojo.getFirstname() + signUpPojo.getLastname() + "@gmail.com";
        signUpPojo.setEmail(email);
        Actions actions = new Actions(Driver.getDriver());
        actions.doubleClick(signUpPage.emailBox).sendKeys(signUpPage.emailBox,email).build().perform();
        //signUpPage.emailBox.sendKeys(email);

    }
    @When("user enters the password as {string}")
    public void user_enters_the_password_as(String firstPassword) {
        firstPassword = faker.internet().password(10,20,true,true);
        signUpPojo.setPassword(firstPassword);
        signUpPage.passwordBox.sendKeys(firstPassword);
        ReusableMethods.waitFor(1);
    }
    @When("user confirms the new password as {string}")
    public void user_confirms_the_new_password_as(String secondPassword) {
        secondPassword = signUpPojo.getPassword();
        System.out.println("secondpassword "+secondPassword);
        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(signUpPage.passwordConfirmationBox).
                sendKeys(signUpPage.passwordConfirmationBox,secondPassword).build().perform();
        ReusableMethods.waitFor(1);

        //signUpPage.passwordConfirmationBox.sendKeys(secondPassword);

    }
    @Then("user clicks on general terms and conditions radio button")
    public void user_clicks_on_general_terms_and_conditions_radio_button() {

        signUpPage.generalTermsAndConditionsBox.click();
        ReusableMethods.waitFor(1);

    }
    @Then("user clicks on create your account button and sees the message as {string}")
    public void user_clicks_on_create_your_account_button_and_sees_the_message_as(String message) {

        signUpPage.createYourAccountButton.click();
        TxtWriter.saveData(signUpPojo);
        ReusableMethods.waitFor(1);

        userAccountPage= new UserAccountPage();
       String actualMessage= userAccountPage.message.getText();
        //System.out.println("actual "+actualMessage);
        Assert.assertEquals(message,actualMessage);

    }
}
