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
        makeTicket();
        makeResult();
        inputView.readClose();
    }

    private void makeResult() {
        while (true) {
            try {
                MultipleNumberRequest winningNumbers = getValidRequest(inputView::readWinningNumbers);
                NumberRequest bonusNumber = getValidRequest(inputView::readBonusNumber);
                outputView.showStatistics(lottoService.calculateResult(winningNumbers, bonusNumber));
                break;
            } catch (IllegalArgumentException e) {
                outputView.printMessage(e.getMessage());
            }
        }
    }

    private void makeTicket() {
        while (true) {
            try {
                NumberRequest purchaseAmount = getValidRequest(inputView::readPurchaseAmount);
                outputView.showPurchaseHistory(lottoService.getLottoTickets(purchaseAmount));
                break;
            } catch (IllegalArgumentException e) {
                outputView.printMessage(e.getMessage());
            }
        }
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
}

