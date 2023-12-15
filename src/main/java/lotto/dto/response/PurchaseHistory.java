package lotto.dto.response;

import lotto.model.Lotto;

import java.util.List;

public class PurchaseHistory {
    List<Lotto> lottoBundle;
    public PurchaseHistory(List<Lotto> lottoBundle) {
        this.lottoBundle = lottoBundle;
    }

    public List<Lotto> getLottoBundle() {
        return lottoBundle;
    }
}
