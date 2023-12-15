package lotto.controller;

import lotto.dto.request.NumberRequest;
import lotto.service.LottoService;
import lotto.view.input.InputView;
import lotto.view.input.OutputView;

public class LottoController {
    LottoService lottoService;
    InputView inputView;
    OutputView outputView;

    public LottoController(InputView inputView, OutputView outputView, LottoService lottoService) {
        this.lottoService = lottoService;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        NumberRequest purchaseAmount = inputView.readPurchaseAmount();
        outputView.showPurchaseHistory(lottoService.getLottoTickets(purchaseAmount));
    }
}
