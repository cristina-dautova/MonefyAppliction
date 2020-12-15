public enum IncomeCategory {

    DEPOSITS(1),
    SALARY(2),
    SAVINGS(3),
    ADD_CATEGORY(4);


    private final int value;
    IncomeCategory(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}