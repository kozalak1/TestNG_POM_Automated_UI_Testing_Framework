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

    @FindBy (xpath = "(//input[@type='text'])[1]")
    public WebElement accountName;

    @FindBy (xpath = "(//input[@type='text'])[2]")
    public WebElement accountEmail;


    @FindBy (xpath = "//input[@id='last_name']")
    public WebElement addressLastName;


    // ----------------- test case_02 -------------------- //



    // ----------------- test case_03 -------------------- //

    @FindBy (xpath = "//p[text()='Your email or password is incorrect!']")
    public WebElement emailAndPasswordIncorrectMsj;

    // ----------------- test case_04 -------------------- //

    @FindBy(xpath = "(//li/a)[4]")
    public WebElement logoutButton;

    // ----------------- test case_05 (signUpPage) -------------------- //



}
