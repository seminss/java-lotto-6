package lotto.service;

import lotto.dto.response.PurchaseHistory;
import lotto.dto.request.NumberRequest;
import lotto.model.*;
import lotto.service.util.LottoMaker;

import java.util.*;

public class LottoService {
    private final LottoMaker lottoMaker;

    public LottoService(LottoMaker lottoMaker) {
        this.lottoMaker = lottoMaker;
    }

    public PurchaseHistory getLottoTickets(NumberRequest purchaseAmount) {
        int lottoCount = getTicketCount(purchaseAmount.getNumber());
        LottoRepository lottoRepository = LottoRepository.of(createLottoBundle(lottoCount));
        return new PurchaseHistory(lottoRepository.getLottoBundle());
    }

/*    public Statistics calculateResult(List<Integer> winningNumberRequest, int bonusNumberRequest, int purchaseAmount) {
        int lottoCount = getTicketCount(purchaseAmount);
        LottoRepository lottoRepository = LottoRepository.of(createLottoBundle(lottoCount));
        Answer answer = new Answer(winningNumberRequest, bonusNumberRequest);
        EnumMap<Rank, Integer> rankResult = lottoRepository.calculateRankCount(answer);
        return new Statistics(rankResult, purchaseAmount);
    }*/

    private List<Lotto> createLottoBundle(int lottoCount) {
        List<Lotto> lottoBundle = new ArrayList<>();
        for (int i = 0; i < lottoCount; i++) {
            lottoBundle.add(createLotto());
        }
        return lottoBundle;
    }

    private static int getTicketCount(int purchaseAmount) {
        return purchaseAmount / Ticket.PRICE;
    }

    private Lotto createLotto() {
        return Lotto.of(lottoMaker.createLottoNumbers());
    }
}
