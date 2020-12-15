import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class CheckBalanceExpenseTest extends TestHelpers{

    @Test(description = "Adds expenses in Bills category via expense button and checks balance on the Balance button")
    public void addBillsExpenseViaExpenseButton() {
        addExpenseToExpenseCategory(ExpenseCategory.BILLS,53);
    }

    @Test(description = "Adds expenses in Car category via expense button and checks balance on the Balance button")
    public void addCarExpenseViaExpenseButton() {
        addExpenseToExpenseCategory(ExpenseCategory.CAR,12);
    }

    @Test(description = "Adds expenses in Clothes category via expense button and checks balance on the Balance button")
    public void addClothesExpenseViaExpenseButton() {
        addExpenseToExpenseCategory(ExpenseCategory.CLOTHES,68);
    }

    @Test(description = "Adds expenses in Communication category via expense button and checks balance on the Balance button")
    public void addCommunicationlExpenseViaExpenseButton() {
        addExpenseToExpenseCategory(ExpenseCategory.COMMUNICATIONS,9);
    }

    @Test(description = "Adds expenses in EatingOut category via expense button and checks balance on the Balance button")
    public void addEatingOutExpenseViaExpenseButton() {
        addExpenseToExpenseCategory(ExpenseCategory.EATING_OUT,30);
    }

    @Test(description = "Adds expenses in Entertainment category via expense button and checks balance on the Balance button")
    public void addEntertainmentExpenseViaExpenseButton() {
        addExpenseToExpenseCategory(ExpenseCategory.ENTERTAINMENT,61);
    }

    @Test(description = "Adds expenses in Food category via expense button and checks balance on the Balance button")
    public void addFoodExpenseViaExpenseButton() {
        addExpenseToExpenseCategory(ExpenseCategory.FOOD,63);
    }

    @Test(description = "Adds expenses in Gifts category via expense button and checks balance on the Balance button")
    public void addGiftsExpenseViaExpenseButton() {
        addExpenseToExpenseCategory(ExpenseCategory.GIFTS,632);
    }

    @Test(description = "Adds expenses in Health category via expense button and checks balance on the Balance button")
    public void addHealthExpenseViaExpenseButton() {
        addExpenseToExpenseCategory(ExpenseCategory.HEALTH,60);
    }

    @Test(description = "Adds expenses in House category via expense button and checks balance on the Balance button")
    public void addHouseExpenseViaExpenseButton() {
        addExpenseToExpenseCategory(ExpenseCategory.HOUSE,123);
    }

    @Test(description = "Adds expenses in Pets category via expense button and checks balance on the Balance button")
    public void addPetsExpenseViaExpenseButton() {
        addExpenseToExpenseCategory(ExpenseCategory.PETS,54);
    }

    @Test(description = "Adds expenses in Sports category via expense button and checks balance on the Balance button")
    public void addSportsExpenseViaExpenseButton() {
        addExpenseToExpenseCategory(ExpenseCategory.SPORTS,141);
    }

    @Test(description = "Adds expenses in Taxi category via expense button and checks balance on the Balance button")
    public void addTaxiExpenseViaExpenseButton() {
        addExpenseToExpenseCategory(ExpenseCategory.TAXI,19);
    }

    @Test(description = "Adds expenses in Toiletry category via expense button and checks balance on the Balance button")
    public void addToiletryExpenseViaExpenseButton() {
        addExpenseToExpenseCategory(ExpenseCategory.TOILETRY,23);
    }

    @Test(description = "Adds expenses in Transport category via expense button and checks balance on the Balance button")
    public void addTransportExpenseViaExpenseButton() {
        addExpenseToExpenseCategory(ExpenseCategory.TRANSPORT,42);
    }


    public void addExpenseToExpenseCategory(ExpenseCategory category, int calculatorButtons){


        MobileElement currentBalanceAmountButton = waitForElementLocatedBy(driver, By.id(MonefyButtons.BALANCE_BUTTON.getValue()));
        String currentBalanceAmountText = currentBalanceAmountButton.getText();
        currentBalanceAmountText = currentBalanceAmountText.replace("$", "").replace(",", "").replace("Balance", "");

        MobileElement expenseButton = waitForElementLocatedBy(driver, By.id(MonefyButtons.EXPENSE_BUTTON.getValue()));
        expenseButton.click();

        List<Integer> digits = convertNumberToDigits(calculatorButtons);
        for (Integer digit : digits) {
            MobileElement calculatorButton = waitForElementLocatedBy(driver, By.id(MonefyButtons.CALCULATOR_BUTTON.getValue() + digit));
            calculatorButton.click();
        }

        MobileElement chooseCategoryButton = waitForElementLocatedBy(driver, By.id(MonefyButtons.CHOOSE_CATEGORY_BUTTON.getValue()));
        chooseCategoryButton.click();

        MobileElement widgetIcon = driver.findElementsByClassName("android.widget.ImageView").get(category.getValue());
        widgetIcon.click();

        double amount = Double.parseDouble(currentBalanceAmountText) - calculatorButtons;

        MobileElement balanceAmountButton = waitForElementLocatedBy(driver, By.id(MonefyButtons.BALANCE_BUTTON.getValue()));
        currentBalanceAmountText = balanceAmountButton.getText();

        currentBalanceAmountText = currentBalanceAmountText.replace("$", "").replace(",", "").replace("Balance", "");

        Assert.assertTrue(currentBalanceAmountText.contains(String.valueOf(amount)));
    }

}
