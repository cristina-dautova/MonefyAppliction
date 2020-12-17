import enums.*;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class CheckPaymentMethodTest extends TestHelpers{

    @Test(description = "Adds expenses using Cash")
    public void addExpenseWithCash() throws InterruptedException {

        addExpenseToExpenseCategory(ExpenseCategory.BILLS,53, PaymentMethods.CASH);

    }

    @Test(description = "Adds expenses using PaymentCard")
    public void addExpenseWithPaymentCard() throws InterruptedException {

        addExpenseToExpenseCategory(ExpenseCategory.BILLS,110, PaymentMethods.PAYMENT_CARD);

    }



    private void addExpenseToExpenseCategory(ExpenseCategory category, int calculatorButtons, PaymentMethods paymentmethod) throws InterruptedException {

        //Remember the sum of current expense
        MobileElement currentExpenseAmountText = waitForElementLocatedBy(driver, By.id(MonefyTextFields.EXPENSE_AMOUNT_TEXT.getValue()));
        String currentExpAmount = currentExpenseAmountText.getText();
        currentExpAmount = currentExpAmount.replace("$", "").replace(",", "");

        //Click Expense Button
        MobileElement expenseButton = driver.findElement(By.id(MonefyButtons.EXPENSE_BUTTON.getValue()));
        expenseButton.click();

        //Add sum
        List<Integer> digits = convertNumberToDigits(calculatorButtons);
        for (Integer digit : digits) {
            MobileElement calculatorButton = waitForElementLocatedBy(driver, By.id(MonefyButtons.CALCULATOR_BUTTON.getValue() + digit));
            calculatorButton.click();
        }

        //Click payment method button
        MobileElement paymentMethod = driver.findElement(By.id(MonefyButtons.PAYMENT_METHOD.getValue()));
        paymentMethod.click();

        waitForElementLocatedBy(driver, By.id(MonefyButtons.PAYMENT_METHOD.getValue()));

        //Choose payment method
        MobileElement choosePaymentMethod = driver.findElements(By.id(MonefyButtons.PAYMENT_METHOD.getValue())).get(paymentmethod.getValue(false));
        choosePaymentMethod.click();

        //Add payment to category
        MobileElement chooseCategoryButton = driver.findElement(By.id(MonefyButtons.CHOOSE_CATEGORY_BUTTON.getValue()));
        chooseCategoryButton.click();


        MobileElement widgetIcon = driver.findElementsByClassName("android.widget.ImageView").get(category.getValue());
        widgetIcon.click();

        //Open left menu
        Thread.sleep(2000);
        swipeScreen(SwipeDirectionPoint.LEFT, SwipeDirectionPoint.CENTER);
        Thread.sleep(2000);

        //Choose payment method in left menu
        MobileElement choosePaymentMethodLeftMenu = driver.findElement(By.id(MonefyButtons.PAYMENT_METHOD.getValue()));
        choosePaymentMethodLeftMenu.click();

        waitForElementLocatedBy(driver, By.id(MonefyButtons.PAYMENT_METHOD.getValue()));

        MobileElement choosePaymentMethod1 = driver.findElements(By.id(MonefyButtons.PAYMENT_METHOD.getValue())).get(paymentmethod.getValue(true));
        choosePaymentMethod1.click();

        //Check if the amount is the same
        MobileElement expenseAmountText = driver.findElement(By.id(MonefyTextFields.EXPENSE_AMOUNT_TEXT.getValue()));
        String expenseAmount = expenseAmountText.getText().replace(",", "");

        double amount = Double.parseDouble(currentExpAmount) + calculatorButtons;

        Assert.assertTrue(expenseAmount.contains(String.valueOf(amount)));
    }







}
