package lotto.dto.request;

import lotto.exception.LottoException;

import java.util.*;

public class MultipleNumberRequest {

    List<Integer> multipleNumber;

    private MultipleNumberRequest(String userInput) {
        validateNotEmpty(userInput);
        validateFormat(userInput);
        this.multipleNumber = parseMultipleNumber(userInput);
    }

    public static MultipleNumberRequest of(String userInput) {
        return new MultipleNumberRequest(userInput);
    }

    public List<Integer> getMultipleNumber() {
        return multipleNumber;
    }

    private void validateNotEmpty(String userInput) {
        if (userInput.isEmpty()) {
            throw new LottoException("입력 값이 없습니다.");
        }
    }

    private void validateFormat(String userInput) {
        if (!userInput.matches("^[0-9]+(,[0-9]+)*$")) {
            throw new LottoException("숫자가 쉼표로 구분 되어 있어야 합니다.");
        }
    }

    private List<Integer> parseMultipleNumber(String userInput) {
        String[] split = userInput.split(",");
        List<Integer> numbers = new ArrayList<>();
        for (String s : split) {
            numbers.add(Integer.parseInt(s));
        }
        return numbers;
    }
}
