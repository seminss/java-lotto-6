package lotto.model;

import java.util.*;

public class LottoRepository {
    private List<Lotto> lottoBundle;

    private LottoRepository(List<Lotto> lottoBundle) {
        this.lottoBundle = lottoBundle;
    }

    public static LottoRepository of(List<Lotto> lottoBundle) {
        return new LottoRepository(lottoBundle);
    }

    public List<Lotto> getLottoBundle() {
        return Collections.unmodifiableList(lottoBundle);
    }

    public EnumMap<Rank, Integer> calculateRankCount(Answer answer) {
        EnumMap<Rank, Integer> rankMap = new EnumMap<>(Rank.class);
        initializeRankAndCount(rankMap);
        for (Lotto lotto : lottoBundle) {
            int matchCount = answer.getMatchCount(lotto);
            boolean bonusStatus = answer.matchBonus(lotto);
            Rank rank = Rank.of(matchCount, bonusStatus);
            rankMap.put(rank, rankMap.get(rank) + 1);
        }
        return rankMap;
    }

    private void initializeRankAndCount(EnumMap<Rank, Integer> rankMap) {
        for (Rank rank : Rank.values()) {
            rankMap.put(rank, 0);
        }
    }
}
