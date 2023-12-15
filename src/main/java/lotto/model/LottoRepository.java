package lotto.model;

import java.util.*;

public class LottoRepository {
    private static final List<Lotto> lottoBundle = new ArrayList<>();

    private LottoRepository() {
    }

    public static void addLotto(Lotto lotto) {
        lottoBundle.add(lotto);
    }

    public static List<Lotto> getLottoBundle() {
        return Collections.unmodifiableList(lottoBundle);
    }

    public static Map<Rank,Integer> calculateRankCount(Answer answer){
        EnumMap<Rank,Integer> rankMap = new EnumMap<>(Rank.class);
        for(Lotto lotto : lottoBundle){
            int matchCount = answer.getMatchCount(lotto);
            boolean bonusStatus = answer.matchBonus(lotto);
            Rank rank = Rank.of(matchCount, bonusStatus);
            rankMap.put(rank, rankMap.getOrDefault(rank, 0) + 1);
        }
        return rankMap;
    }
}
