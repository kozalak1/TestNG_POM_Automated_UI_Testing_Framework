package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.function.Function;

public class ReusableMethods {



    // sitenin ana sayfasına gitmek ve ana sayfanın görünür olması sürekli gerekli olacagı için ayrı bir metod haline getirildi

    public static void homePageNavigateAndVisible() {

        // 1. Launch browser
        // 2. Navigate to url 'http://automationexercise.com'

        Driver.getDriver().get(ConfigReader.getProperty("aeUrl"));

        //  3. Verify that home page is visible successfully

        String actualHomePageTitle = Driver.getDriver().getTitle();
        System.out.println(actualHomePageTitle);
        String expectedHomePageTitle = ConfigReader.getProperty("title");
        Assert.assertEquals(actualHomePageTitle, expectedHomePageTitle);

    }


    //======Fluent Wait====//
    public static WebElement fluentWait(final WebElement webElement, int timeout) {
        //FluentWait<WebDriver> wait = new FluentWait<WebDriver>(Driver.getDriver()).withTimeout(timeinsec, TimeUnit.SECONDS).pollingEvery(timeinsec, TimeUnit.SECONDS);
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(Driver.getDriver())
                .withTimeout(Duration.ofSeconds(3))//Wait 3 second each time
                .pollingEvery(Duration.ofSeconds(1));//Check for the element every 1 second

        WebElement element = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return webElement;
            }
        });

        return element;
    }

}
