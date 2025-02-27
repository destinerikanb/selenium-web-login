import org.destinenb.utils.Util;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest{
    @Test
    public void loginTest(){
        driver.get(Util.BASE_URL);

        var userID = By.name("uid");
        var password = By.name("password");
        var loginButton = By.name("btnLogin");

        //clear a field before use
        driver.findElement(userID).clear();
        driver.findElement(userID).sendKeys(Util.USERNAME);
        driver.findElement(password).clear();
        driver.findElement(password).sendKeys(Util.PASSWORD);
        driver.findElement(loginButton).click();

        String title = driver.getTitle();
        Assert.assertEquals(title, "Guru99 Bank Manager HomePage", "Login Failed");
    }
}
