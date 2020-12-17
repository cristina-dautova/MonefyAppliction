package enums;

public enum MonefyButtons {

    CHOOSE_CATEGORY_BUTTON ("com.monefy.app.lite:id/keyboard_action_button"),
    CALCULATOR_BUTTON("com.monefy.app.lite:id/buttonKeyboard"),
    INCOME_BUTTON("com.monefy.app.lite:id/income_button_title"),
    EXPENSE_BUTTON("com.monefy.app.lite:id/expense_button_title"),
    CALCULATOR_PLUS_BUTTON("com.monefy.app.lite:id/buttonKeyboardPlus"),
    CALCULATOR_MINUS_BUTTON("com.monefy.app.lite:id/buttonKeyboardMinus"),
    CALCULATOR_EQUALS_BUTTON("com.monefy.app.lite:id/buttonKeyboardEquals"),
    CALCULATOR_DOT_BUTTON("com.monefy.app.lite:id/buttonKeyboardDot"),
    CALCULATOR_MULTIPLY_BUTTON("com.monefy.app.lite:id/buttonKeyboardMultiply"),
    CALCULATOR_DIVIDE_BUTTON("com.monefy.app.lite:id/buttonKeyboardDivide"),
    BALANCE_BUTTON("com.monefy.app.lite:id/balance_amount"),
    PAYMENT_METHOD("com.monefy.app.lite:id/icon");



    private final String value;

    MonefyButtons(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

