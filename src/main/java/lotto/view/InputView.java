package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.dto.request.MultipleNumberRequest;
import lotto.dto.request.NumberRequest;

public class InputView {

    public NumberRequest readPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        String userInput = read();
        return NumberRequest.of(userInput);
    }

    public MultipleNumberRequest readWinningNumbers() {
        System.out.println("\n당첨 번호를 입력해 주세요.");
        String userInput = read();
        return MultipleNumberRequest.of(userInput);
    }

    public NumberRequest readBonusNumber() {
        System.out.println("\n보너스 번호를 입력해 주세요.");
        String userInput = read();
        return NumberRequest.of(userInput);
    }

    public void readClose() {
        Console.close();
    }

    private String read() {
        return Console.readLine();
    }
}