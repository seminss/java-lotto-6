package lotto.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
}
