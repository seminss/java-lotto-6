package lotto.service;

import lotto.dto.request.MultipleNumberRequest;
import lotto.dto.response.PurchaseHistory;
import lotto.dto.request.NumberRequest;
import lotto.dto.response.Statistics;
import lotto.model.*;
import lotto.service.util.LottoMaker;

import java.util.*;

public class LottoService {
    private final LottoMaker lottoMaker;
    private LottoRepository lottoRepository;

    public LottoService(LottoMaker lottoMaker) {
        this.lottoMaker = lottoMaker;
    }

    public PurchaseHistory getLottoTickets(NumberRequest purchaseAmount) {
        int lottoCount = getTicketCount(purchaseAmount.getNumber());
        lottoRepository = LottoRepository.of(createLottoBundle(lottoCount));
        return new PurchaseHistory(lottoRepository.getLottoBundle());
    }

    public Statistics calculateResult(MultipleNumberRequest winningNumberRequest,
                                      NumberRequest bonusNumberRequest, NumberRequest purchaseAmount) {
        Answer answer = new Answer(winningNumberRequest.getMultipleNumber(), bonusNumberRequest.getNumber());
        EnumMap<Rank, Integer> rankResult = lottoRepository.calculateRankCount(answer);
        double profit = calculateProfitRate(rankResult, purchaseAmount.getNumber());
        return new Statistics(rankResult, profit);
    }

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

    private double calculateProfitRate(EnumMap<Rank, Integer> rankResult, int purchaseAmount) {
        long totalPrize = 0;
        for (Rank rank : Rank.values()) {
            totalPrize += rank.calculateWinningMoney(rankResult.get(rank));
        }
        return (double) totalPrize / purchaseAmount;
    }
}
