package lotto.model.vo;

import lotto.exception.LottoException;
import lotto.model.Ticket;

public class PurchaseAmount {
    int amount;

    public PurchaseAmount(int amount) {
        validatePositive(amount);
        validateUnit(amount);
        this.amount = amount;
    }

    public static PurchaseAmount of(int amount) {
        return new PurchaseAmount(amount);
    }

    public int getAmount() {
        return amount;
    }

    private void validatePositive(int number) {
        if (number <= 0) {
            throw new LottoException("0보다 큰 숫자를 입력해주세요.");
        }
    }

    private void validateUnit(int number) {
        if (number % Ticket.LOTTO_PRICE.getValue() != 0) {
            throw new LottoException("1000원 단위로 입력해주세요.");
        }
    }
}
