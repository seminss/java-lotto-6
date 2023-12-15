package lotto.model;

public enum Ticket {
    LOTTO_PRICE(1000),
    LOTTO_NUMBER_COUNT(6),
    LOTTO_NUMBER_MIN(1),
    LOTTO_NUMBER_MAX(45),
    ;

    private final int value;

    Ticket(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public int getLottoCount(int money) {
        return money / value;
    }
}
