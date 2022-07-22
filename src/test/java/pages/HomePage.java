package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class HomePage {

public HomePage () {

    PageFactory.initElements(Driver.getDriver(), this);
}

    // ----------------- test case_01 -------------------- //

    @FindBy (xpath ="//a[@href='/login']")
    public WebElement loginButton;

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

    @FindBy (xpath = "(//input[@type='text'])[1]")
    public WebElement accountName;

    @FindBy (xpath = "(//input[@type='text'])[2]")
    public WebElement accountEmail;

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

    @FindBy (xpath = "//input[@id='last_name']")
    public WebElement addressLastName;

    @FindBy (xpath = "//a[text()='Continue']")
    public WebElement continueButton;

    @FindBy (xpath = "//a[text()=' Logged in as ']")
    public WebElement loggedInText;

    @FindBy (xpath = "(//li/a)[5]")
    public WebElement deleteAccountButton;


}
