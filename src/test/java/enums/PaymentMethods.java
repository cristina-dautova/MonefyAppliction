package enums;

public enum PaymentMethods {

    All_CURRENCIES(0),
    CASH(1),
    PAYMENT_CARD(2);


    private final int value;
    PaymentMethods(int value) {
        this.value = value;
    }

    public int getValue(boolean withAllCurrencies) {
        return withAllCurrencies ? value : (value-1);
    }

}
