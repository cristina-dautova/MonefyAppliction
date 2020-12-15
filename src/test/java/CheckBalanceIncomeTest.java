import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class CheckBalanceIncomeTest extends TestHelpers{

    @Test(description = "Adds income via income button to Deposits")
    public void addIncomeViaIncomeButtonToDeposits() {
        addIncomeToIncomeCategory(IncomeCategory.DEPOSITS, 5000, "N26");
    }

    @Test(description = "Adds income via income button to Salary")
    public void addIncomeViaIncomeButtonToSalary() {
        addIncomeToIncomeCategory(IncomeCategory.SALARY, 3500, "work");
    }

    @Test(description = "Adds income via income button to Savings")
    public void addIncomeViaIncomeButtonToSavings() {
        addIncomeToIncomeCategory(IncomeCategory.SAVINGS, 10000, "");
    }

    private void addIncomeToIncomeCategory(IncomeCategory category, int calculatorButtons, String note){

        MobileElement currentBalanceAmountButton = waitForElementLocatedBy(driver, By.id(MonefyTextFields.INCOME_AMOUNT_TEXT.getValue()));
        String currentBalanceAmountText = currentBalanceAmountButton.getText();
        currentBalanceAmountText = currentBalanceAmountText.replace("$", "").replace(",", "").replace("Balance", "");

        MobileElement incomeButton = waitForElementLocatedBy(driver, By.id(MonefyButtons.INCOME_BUTTON.getValue()));
        incomeButton.click();

        List<Integer> digits = convertNumberToDigits(calculatorButtons);
        for (Integer digit : digits) {
            MobileElement calculatorButton = waitForElementLocatedBy(driver, By.id(MonefyButtons.CALCULATOR_BUTTON.getValue() + digit));
            calculatorButton.click();
        }

        MobileElement noteText = waitForElementLocatedBy(driver, By.id(MonefyTextFields.NOTE_TEXT.getValue()));
        noteText.sendKeys(note);

        MobileElement chooseCategoryButton = waitForElementLocatedBy(driver, By.id(MonefyButtons.CHOOSE_CATEGORY_BUTTON.getValue()));
        chooseCategoryButton.click();

        MobileElement widgetIcon = driver.findElementsByClassName("android.widget.ImageView").get(category.getValue());
        widgetIcon.click();

        double amount = Double.parseDouble(currentBalanceAmountText) + calculatorButtons;

        MobileElement balanceAmountButton = waitForElementLocatedBy(driver, By.id(MonefyButtons.BALANCE_BUTTON.getValue()));
        currentBalanceAmountText = balanceAmountButton.getText();

        currentBalanceAmountText = currentBalanceAmountText.replace("$", "").replace(",", "").replace("Balance", "");

        Assert.assertTrue(currentBalanceAmountText.contains(String.valueOf(amount)));    }
}

