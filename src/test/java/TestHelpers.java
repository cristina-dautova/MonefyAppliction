import enums.ExpenseCategory;
import enums.MonefyButtons;
import enums.MonefyTextFields;
import enums.SwipeDirectionPoint;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
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

    protected AndroidDriver<AndroidElement> openApplication() throws MalformedURLException {

        File file = new File("src/test/resources/com.monefy.app.lite_2020-11-12.apk");
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability(MobileCapabilityType.DEVICE_NAME, "AndroidEmulator");
        dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        dc.setCapability(MobileCapabilityType.APP, file.getAbsolutePath());

        return new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), dc);
    }

    protected MobileElement waitForElementLocatedBy(AndroidDriver driver, By by) {
        return (MobileElement) new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(by));
    }

    protected List<Integer> convertNumberToDigits (Integer number){
        List<Integer> digits = new ArrayList<>();
        String strNumber = number.toString();
        for (int i = 0; i < strNumber.length(); i++) {
            digits.add(Integer.parseInt(String.valueOf(strNumber.charAt(i))));
        }
        return digits;
    }

    protected void swipeScreen(SwipeDirectionPoint startPoint, SwipeDirectionPoint endPoint) {

        final int ANIMATION_TIME = 200;
        final int PRESS_TIME = 200;

        PointOption pointOptionStart = getPointOption(startPoint);
        PointOption pointOptionEnd = getPointOption(endPoint);

        try {
            new TouchAction(driver)
                    .press(pointOptionStart)
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
                    .moveTo(pointOptionEnd)
                    .release().perform();
        } catch (Exception e) {
            System.err.println("swipeScreen(): TouchAction FAILED\n" + e.getMessage());
            return;
        }

        try {
            Thread.sleep(ANIMATION_TIME);
        } catch (InterruptedException e) {
        }
    }

    private PointOption getPointOption(SwipeDirectionPoint enumPoint) {
        int edgeBorder = 10;
        Dimension dims = driver.manage().window().getSize();
        PointOption pointOption;
        switch (enumPoint) {
            case DOWN:
                pointOption = PointOption.point(dims.width / 2, dims.height - edgeBorder);
                break;
            case UP:
                pointOption = PointOption.point(dims.width / 2, edgeBorder);
                break;
            case LEFT:
                pointOption = PointOption.point(edgeBorder, dims.height / 2);
                break;
            case RIGHT:
                pointOption = PointOption.point(dims.width - edgeBorder, dims.height / 2);
                break;
            case CENTER:
                pointOption = PointOption.point(dims.width / 2, dims.height / 2);
                break;
            default:
                throw new IllegalArgumentException("swipeScreen(): dir: '" + enumPoint + "' NOT supported");
        }
        return pointOption;
    }

    protected void addExpenseToExpenseCategory(ExpenseCategory category, int calculatorButtons){

        MobileElement currentExpenseAmountText = waitForElementLocatedBy(driver, By.id(MonefyTextFields.EXPENSE_AMOUNT_TEXT.getValue()));
        String currentExpAmount = currentExpenseAmountText.getText();
        currentExpAmount = currentExpAmount.replace("$", "").replace(",", "");

        MobileElement expenseButton = driver.findElement(By.id(MonefyButtons.EXPENSE_BUTTON.getValue()));
        expenseButton.click();

        List<Integer> digits = convertNumberToDigits(calculatorButtons);
        for (Integer digit : digits) {
            MobileElement calculatorButton = waitForElementLocatedBy(driver, By.id(MonefyButtons.CALCULATOR_BUTTON.getValue() + digit));
            calculatorButton.click();
        }

        MobileElement chooseCategoryButton = driver.findElement(By.id(MonefyButtons.CHOOSE_CATEGORY_BUTTON.getValue()));
        chooseCategoryButton.click();

        MobileElement widgetIcon = driver.findElementsByClassName("android.widget.ImageView").get(category.getValue());
        widgetIcon.click();

        MobileElement expenseAmountText = driver.findElement(By.id(MonefyTextFields.EXPENSE_AMOUNT_TEXT.getValue()));
        String expenseAmount = expenseAmountText.getText().replace(",", "");

        double amount = Double.parseDouble(currentExpAmount) + calculatorButtons;

        Assert.assertTrue(expenseAmount.contains(String.valueOf(amount)));
    }


}

