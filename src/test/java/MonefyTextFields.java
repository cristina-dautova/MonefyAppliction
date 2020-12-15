public enum MonefyTextFields {

    NOTE_TEXT("com.monefy.app.lite:id/textViewNote"),
    EXPENSE_AMOUNT_TEXT ("com.monefy.app.lite:id/expense_amount_text"),
    INCOME_AMOUNT_TEXT("com.monefy.app.lite:id/income_amount_text");




    private final String value;

    MonefyTextFields(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}