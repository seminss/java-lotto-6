package lotto.service;

import lotto.dto.request.MultipleNumberRequest;
import lotto.dto.response.PurchaseHistory;
import lotto.dto.request.NumberRequest;
import lotto.dto.response.Statistics;
import lotto.model.*;
import lotto.model.vo.PurchaseAmount;
import lotto.service.util.LottoMaker;

import java.util.*;

public class LottoService {
    private static final double PERCENT_MULTIPLIER = 100;
    private static final Integer DEFAULT_NUMBER = 0;
    private static final Integer ADD_NUMBER = 1;
    private final LottoMaker lottoMaker;
    private PurchaseAmount amount;
    private LottoRepository lottoRepository;

    public LottoService(LottoMaker lottoMaker) {
        this.lottoMaker = lottoMaker;
    }

    public PurchaseHistory getLottoTickets(NumberRequest purchaseAmount) {
        amount = PurchaseAmount.of(purchaseAmount.getNumber());
        lottoRepository = LottoRepository.of(createLottoBundle(amount));
        return new PurchaseHistory(lottoRepository.getLottoBundle());
    }

    public Statistics calculateResult(MultipleNumberRequest winningNumberRequest, NumberRequest bonusNumberRequest) {
        Answer answer = new Answer(winningNumberRequest.getMultipleNumber(), bonusNumberRequest.getNumber());
        EnumMap<Rank, Integer> rankResult = lottoRepository.calculateCountPerRank(answer);
        double profit = calculateProfitRate(rankResult, amount.getAmount());
        return new Statistics(rankResult, profit);
    }

    private List<Lotto> createLottoBundle(PurchaseAmount amount) {
        int lottoCount = getTicketCount(amount.getAmount());
        List<Lotto> lottoBundle = new ArrayList<>();
        for (int i = DEFAULT_NUMBER; i < lottoCount; i += ADD_NUMBER) {
            lottoBundle.add(createLotto());
        }
        return lottoBundle;
    }

    private int getTicketCount(int purchaseAmount) {
        return purchaseAmount / Ticket.PRICE;
    }

    private Lotto createLotto() {
        return Lotto.of(lottoMaker.createLottoNumbers());
    }

    private double calculateProfitRate(EnumMap<Rank, Integer> rankResult, int purchaseAmount) {
        long totalPrize = DEFAULT_NUMBER;
        for (Rank rank : Rank.values()) {
            totalPrize += rank.calculateWinningMoney(rankResult.get(rank));
        }
        return (double) totalPrize / purchaseAmount * PERCENT_MULTIPLIER;
    }
}
