package lotto.model;

import lotto.exception.LottoException;

import java.util.List;

public class Answer {
    private final List<Integer> winningNumbers;
    private final int bonusNumber;

    public Answer(List<Integer> winningNumbers, int bonusNumber) {
        validate(winningNumbers);
        validateRange(winningNumbers);
        validateRange(bonusNumber);
        validateDuplicate(winningNumbers, bonusNumber);
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    public static Answer of(List<Integer> winningNumbers, int bonusNumber) {
        return new Answer(winningNumbers, bonusNumber);
    }

    public int getMatchCount(Lotto lotto) {
        return (int) winningNumbers.stream()
                .filter(lotto.getNumbers()::contains)
                .count();
    }

    public boolean matchBonus(Lotto lotto) {
        return lotto.getNumbers().contains(bonusNumber);
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new LottoException("정답 번호는 6개여야 합니다.");
        }
    }

    private void validateDuplicate(List<Integer> numbers, int bonusNumber) {
        numbers.add(bonusNumber);
        if (numbers.stream().distinct().count() != 7) {
            throw new LottoException("정답 번호와 보너스 번호는 모두 중복될 수 없습니다.");
        }
    }

    private void validateRange(List<Integer> numbers) {
        for (Integer number : numbers) {
            if (number < 1 || number > 45) {
                throw new LottoException("숫자 범위는 1부터 45까지 입니다.");
            }
        }
    }

    private void validateRange(Integer number) {
        if (number < 1 || number > 45) {
            throw new LottoException("숫자 범위는 1부터 45까지 입니다.");
        }
    }
}
