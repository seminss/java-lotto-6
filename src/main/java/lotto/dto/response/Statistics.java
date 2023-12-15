package lotto.dto.response;

import lotto.model.Rank;

import java.util.EnumMap;

public class Statistics {

    double profitRate;
    EnumMap<Rank, Integer> rankResult;

    public Statistics(EnumMap<Rank, Integer> rankResult, double profitRate) {
        this.rankResult = rankResult;
        this.profitRate = profitRate;
    }

    public double getProfitRate() {
        return profitRate;
    }

    public EnumMap<Rank, Integer> getRankResult() {
        return rankResult;
    }
}
