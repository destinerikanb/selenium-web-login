package org.destinenb.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {

    public static void takeScreenshot(WebDriver driver, String fileName){
        File folder = new File("src/main/java/org/destinenb/screenshots");
        if (!folder.exists()){
            folder.mkdirs();
        }
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String filePath = folder + "/" + fileName + "_" + timeStamp + ".png";

        try{
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File(filePath));

            System.out.println("Screenshot berhasil disimpan di: " + filePath);
        } catch (IOException e) {
            System.out.println("Gagal menyimpan screenshot: " + e.getMessage());
        }
    }

    public static void takeFullScreenShot(String filename) {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String filePath = "src/main/java/org/destinenb/screenshots/" + filename + "_" + timeStamp + ".png";

        try {
            Robot robot = new Robot();
            Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage screenFullImage = robot.createScreenCapture(screenRect);

            File outputFile = new File(filePath);
            ImageIO.write(screenFullImage, "png", outputFile);
            System.out.println("Full Screenshot tersimpan di: " + filePath);
        } catch (IOException | AWTException e) {
            System.out.println("Gagal menyimpan screenshot layar penuh: " + e.getMessage());
        }
    }
}
