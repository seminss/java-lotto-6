package lotto.controller;

import lotto.dto.request.MultipleNumberRequest;
import lotto.dto.request.NumberRequest;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.function.Supplier;

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
        runMethod(this::makeTicket);
        runMethod(this::makeResult);
        inputView.readClose();
    }

    private void makeResult() {
        MultipleNumberRequest winningNumbers = getValidRequest(inputView::readWinningNumbers);
        NumberRequest bonusNumber = getValidRequest(inputView::readBonusNumber);
        outputView.showStatistics(lottoService.calculateResult(winningNumbers, bonusNumber));
    }

    private void makeTicket() {
        NumberRequest purchaseAmount = getValidRequest(inputView::readPurchaseAmount);
        outputView.showPurchaseHistory(lottoService.getLottoTickets(purchaseAmount));
    }

    private <T> T getValidRequest(Supplier<T> inputSupplier) {
        while (true) {
            try {
                return inputSupplier.get();
            } catch (IllegalArgumentException e) {
                outputView.printMessage(e.getMessage());
            }
        }
    }

    private void runMethod(Runnable function) {
        while (true) {
            try {
                function.run();
                break;
            } catch (IllegalArgumentException e) {
                outputView.printMessage(e.getMessage());
            }
        }
    }
}

