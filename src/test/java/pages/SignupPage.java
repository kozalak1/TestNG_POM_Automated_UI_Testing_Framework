package pages;

import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class SignupPage {

    public SignupPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }


}
