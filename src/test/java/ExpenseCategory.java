public enum ExpenseCategory{

    BILLS(1),
    CAR(2),
    CLOTHES(3),
    COMMUNICATIONS(4),
    EATING_OUT(5),
    ENTERTAINMENT(6),
    FOOD(7),
    GIFTS(8),
    HEALTH(9),
    HOUSE(10),
    PETS(11),
    SPORTS(12),
    TAXI(13),
    TOILETRY(14),
    TRANSPORT(15),
    ADD_CATEGORY(16);

    private final int value;
    ExpenseCategory(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
