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

    // ----------------- test case_02 -------------------- //

    @FindBy (xpath = "//div/a/img [1]")
    public WebElement homePageTitle;

    @FindBy (xpath = "//div/h2[1]")
    public WebElement loginYourAccount;

    @FindBy(xpath = "//input[@type='email'][1]")
    public WebElement loginEmail;

    @FindBy(xpath = "//input[@type='password'][1]")
    public WebElement loginPassword;

    @FindBy(xpath = "(//button[@type='submit'])[1]")
    public WebElement loginSubmitButton;

    @FindBy(xpath = "(//li/a)[4]")
    public WebElement logoutButton;

    @FindBy (xpath = "//li/a/b")
    public WebElement loggedUsername;

    // ----------------- test case_03 -------------------- //

    @FindBy (xpath = "//p[text()='Your email or password is incorrect!']")
    public WebElement emailAndPasswordIncorrectMsj;

    // ----------------- test case_05 (signUpPage) -------------------- //
    @FindBy (xpath = "//p[text()='Email Address already exist!']")
    public WebElement emailExistMsj;


}
