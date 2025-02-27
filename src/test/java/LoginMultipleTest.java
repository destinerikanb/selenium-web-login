import org.destinenb.utils.ExcelUtils;
import org.destinenb.utils.ScreenshotUtil;
import org.destinenb.utils.Util;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.FileNotFoundException;

public class LoginMultipleTest extends BaseTest {
    @DataProvider(name = "LoginData")
    public Object[][] getData() throws FileNotFoundException {
        return ExcelUtils.getTestData("Login");
    }

    @Test(dataProvider = "LoginData")
    public void LoginTest(String username, String password) throws InterruptedException {
        driver.get(Util.BASE_URL);

        var usernameField = By.name("uid");
        var passField = By.name("password");
        var loginButton = By.name("btnLogin");

        driver.findElement(usernameField).clear();
        driver.findElement(usernameField).sendKeys(username);
        Thread.sleep(3000);
        driver.findElement(passField).clear();
        driver.findElement(passField).sendKeys(password);
        ScreenshotUtil.takeScreenshot(driver, "login_input");
        Thread.sleep(3000);
        driver.findElement(loginButton).click();
        Thread.sleep(3000);
        String expectedTitle = "Guru99 Bank Manager HomePage";

        try{
            //Wait if there is an alert present
            Thread.sleep(2000);

            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();

            Assert.assertEquals(alertText, Util.LOGIN_ALERT, "Login alert message is not match!");
            Assert.assertNotEquals(alertText, expectedTitle);
            System.out.println("Login Failed!");
        } catch (NoAlertPresentException e){
            String actualTitle = driver.getTitle();
            Assert.assertEquals(actualTitle, expectedTitle, "Login Failed!");
            System.out.println("Login Success!");

            //welcome text
            String actualWelcomeMessage = driver.findElement(By.cssSelector("marquee.heading3")).getText();
            String actualWelcomeManager = driver.findElement(By.cssSelector("tr.heading3")).getText();

            String expectedWelcomeMessage = "Welcome To Manager's Page of Guru99 Bank";
            String expectedWelcomeManager = "Manger Id : " + username;

            Assert.assertEquals(actualWelcomeMessage, expectedWelcomeMessage, "Test Failed");
            Assert.assertEquals(actualWelcomeManager, expectedWelcomeManager, "Test Failed");
        }

    }

}
