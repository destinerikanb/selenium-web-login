import org.destinenb.utils.ScreenshotUtil;
import org.destinenb.utils.Util;
import org.destinenb.utils.WebDriverSetup;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    protected WebDriver driver;


    @BeforeClass
    public void setUp(){
        driver = WebDriverSetup.getWebDriver();
        driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDown(){
        try{
            Thread.sleep(3000);
        } catch(InterruptedException exc){
            exc.printStackTrace();
        }

        driver.quit();

    }

    @AfterMethod
    public void takeScreenshot(ITestResult result){
        try{
            Alert alert = driver.switchTo().alert();
            ScreenshotUtil.takeFullScreenShot(result.getName());
            alert.accept();
        }catch (NoAlertPresentException e){
            ScreenshotUtil.takeFullScreenShot(result.getName());
        }
    }
}
