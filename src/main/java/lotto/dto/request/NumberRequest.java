package lotto.dto.request;

import lotto.exception.LottoException;

public class NumberRequest {
    private final int number;

    private NumberRequest(String userInput) {
        validateNotEmpty(userInput);
        validateFormat(userInput);
        number = parseInteger(userInput);
    }

    public static NumberRequest of(String userInput) {
        return new NumberRequest(userInput);
    }

    public int getNumber() {
        return number;
    }

    private int parseInteger(String userInput) {
        return Integer.parseInt(userInput);
    }

    private void validateNotEmpty(String userInput) {
        if (userInput.isEmpty()) {
            throw new LottoException("입력값이 없습니다.");
        }
    }

    private void validateFormat(String userInput) {
        try {
            Integer.parseInt(userInput);
        } catch (NumberFormatException e) {
            throw new LottoException("숫자가 아닙니다.");
        }
    }

}
