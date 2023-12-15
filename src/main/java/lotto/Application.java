package lotto;

import lotto.controller.LottoController;
import lotto.service.LottoService;
import lotto.service.util.AutoLottoMaker;
import lotto.view.input.InputView;
import lotto.view.input.OutputView;

public class Application {
    public static void main(String[] args) {
        LottoController lottoController = new LottoController(
                new InputView(),
                new OutputView(),
                new LottoService(new AutoLottoMaker())
        );
        lottoController.start();
    }
}
