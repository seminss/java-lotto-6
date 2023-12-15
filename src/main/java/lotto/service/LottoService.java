package lotto.service;

import lotto.model.*;
import lotto.service.util.LottoMaker;

import java.util.*;

public class LottoService {
    private final LottoMaker lottoMaker;

    public LottoService(LottoMaker lottoMaker) {
        this.lottoMaker = lottoMaker;
    }

    public Statistics calculateResult(List<Integer> winningNumberRequest, int bonusNumberRequest, int purchaseAmount) {
        createLottoBundle(getTicketCount(purchaseAmount));
        Answer answer = new Answer(winningNumberRequest, bonusNumberRequest);
        EnumMap<Rank, Integer> rankResult = LottoRepository.calculateRankCount(answer);
        return new Statistics(rankResult, purchaseAmount);
    }

    private void createLottoBundle(int lottoCount) {
        for (int i = 0; i < lottoCount; i++) {
            createLotto();
        }
    }

    private static int getTicketCount(int purchaseAmount) {
        return purchaseAmount / Ticket.PRICE;
    }

    private void createLotto() {
        Lotto lotto = Lotto.of(lottoMaker.createLottoNumbers());
        LottoRepository.addLotto(lotto);
    }
}
