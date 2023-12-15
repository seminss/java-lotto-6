package lotto.model;

import java.util.EnumMap;

public class Statistics {

    double profitRate;
    EnumMap<Rank, Integer> rankResult;

    public Statistics(EnumMap<Rank, Integer> rankResult, int purchaseAmount){
        this.rankResult = rankResult;
        this.profitRate = calculateProfitRate(rankResult, purchaseAmount);
    }

    private double calculateProfitRate(EnumMap<Rank, Integer> rankResult, int purchaseAmount) {
        long totalPrize = 0;
        for (Rank rank : Rank.values()) {
            totalPrize += rank.calculateWinningMoney(rankResult.get(rank));
        }
        return (double) totalPrize / purchaseAmount;
    }
}
