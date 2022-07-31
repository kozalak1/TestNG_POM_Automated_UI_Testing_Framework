package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class SignupPage {

    public SignupPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath ="//a[@href='/login']")
    public WebElement signuploginButton;

    @FindBy (xpath = "//h2[text()='New User Signup!']")
    public WebElement newUserSignText;

    @FindBy (xpath = "//input[@type='text']")
    public WebElement nameBox;

    @FindBy (xpath = "(//input[@type='email'])[2]")
    public WebElement emailBox;

    @FindBy (xpath = "(//button[@type='submit'])[2]")
    public WebElement signupButton;

    @FindBy (xpath = "//div/h2/b")
    public WebElement enterAccountInfo;

    @FindBy (xpath = "(//input[@type='radio'])[1]")
    public WebElement accountTitleRadioButton;

    @FindBy (xpath = "//input[@type='password']")
    public WebElement accountPassword;

    @FindBy (xpath = "//select[@id='days']")
    public WebElement dateOfBirth_days;

    @FindBy (xpath = "//select[@id='months']")
    public WebElement dateOfBirth_months;

    @FindBy (xpath = "//select[@id='years']")
    public WebElement dateOfBirth_years;

    @FindBy (xpath = "//input[@id='newsletter']")
    public WebElement checkboxNewsletter;

    @FindBy (xpath = "//input[@id='optin']")
    public WebElement checkboxOptin;

    @FindBy (xpath = "//input[@id='first_name']")
    public WebElement addressFirstName;

    @FindBy (xpath = "//a[text()='Continue']")
    public WebElement continueButton;

    @FindBy (xpath = "//a[text()=' Logged in as ']")
    public WebElement loggedInText;

    @FindBy (xpath = "(//li/a)[5]")
    public WebElement deleteAccountButton;

}
