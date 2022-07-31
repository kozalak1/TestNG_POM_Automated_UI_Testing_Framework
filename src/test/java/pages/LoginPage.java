package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class LoginPage {

    public LoginPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }


    @FindBy(xpath = "//div/a/img [1]")
    public WebElement homePageTitle;

    @FindBy (xpath = "//div/h2[1]")
    public WebElement loginYourAccount;

    @FindBy(xpath = "(//input[@type='email'])[1]")
    public WebElement loginEmail;

    @FindBy(xpath = "//input[@type='password'][1]")
    public WebElement loginPassword;

    @FindBy(xpath = "(//button[@type='submit'])[1]")
    public WebElement loginSubmitButton;

    @FindBy (xpath = "//li/a/b")
    public WebElement loggedUsername;

    @FindBy (xpath = "//p[text()='Email Address already exist!']")
    public WebElement emailExistMsj;

}
