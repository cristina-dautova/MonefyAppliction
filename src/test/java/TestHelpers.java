import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class TestHelpers {

    protected AndroidDriver<AndroidElement> driver;

    @BeforeClass()
    public void applicationSetup() throws MalformedURLException {
        driver =  openApplication();
    }

    @AfterClass()
    public void applicationReset() throws InterruptedException {
        driver.closeApp();
        Thread.sleep(10000);
    }

    public static AndroidDriver<AndroidElement> openApplication() throws MalformedURLException {

        File file = new File("src/test/resources/com.monefy.app.lite_2020-11-12.apk");
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability(MobileCapabilityType.DEVICE_NAME, "AndroidEmulator");
        dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        dc.setCapability(MobileCapabilityType.APP, file.getAbsolutePath());

        return new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), dc);
    }

    public static MobileElement waitForElementLocatedBy(WebDriver driver, By by) {
        return (MobileElement) new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public static List<Integer> convertNumberToDigits (Integer number){
        List<Integer> digits = new ArrayList<Integer>();
        String strNumber = number.toString();
        for (int i = 0; i < strNumber.length(); i++) {
            digits.add(Integer.parseInt(String.valueOf(strNumber.charAt(i))));
        }
        return digits;
    }


}

