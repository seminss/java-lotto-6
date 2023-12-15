package lotto.model;

import java.util.*;

public class Answer {
    private final List<Integer> winningNumbers;
    private final int bonusNumber;

    public Answer(List<Integer> winningNumbers, int bonusNumber) {
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    public static Answer of(List<Integer> winningNumbers, int bonusNumber) {
        return new Answer(winningNumbers, bonusNumber);
    }

    public List<Integer> getWinningNumbers() {
        return winningNumbers;
    }

    public int getBonusNumber() {
        return bonusNumber;
    }

    public int getMatchCount(Lotto lotto) {
        return (int) winningNumbers.stream()
                .filter(lotto.getNumbers()::contains)
                .count();
    }

    public boolean matchBonus(Lotto lotto) {
        return lotto.getNumbers().contains(bonusNumber);
    }
}
