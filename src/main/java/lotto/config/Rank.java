package lotto.config;

public enum Rank {
    FIRST(6, 2_000_000_000, false),
    SECOND(5, 30_000_000, true),
    THIRD(5, 1_500_000, false),
    FOURTH(4, 50_000, false),
    FIFTH(3, 5_000, false),
    MISS(0, 0, false),
    ;

    private final int matchCount;
    private final int winningMoney;
    private final boolean needBonus;

    Rank(int matchCount, int winningMoney, boolean needBonus) {
        this.matchCount = matchCount;
        this.winningMoney = winningMoney;
        this.needBonus = needBonus;
    }

    public static Rank of(int matchCount, boolean matchBonus) {
        if (matchCount == SECOND.matchCount && matchBonus) {
            return SECOND;
        }
        for (Rank rank : values()) {
            if (rank.matchCount == matchCount) {
                return rank;
            }
        }
        throw new IllegalArgumentException();
    }

    public int calculateWinningMoney(int count) {
        return winningMoney * count;
    }

    public int getWinningMoney() {
        return winningMoney;
    }

    public int getMatchCount() {
        return matchCount;
    }
}
