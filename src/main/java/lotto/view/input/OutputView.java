package lotto.view.input;

import lotto.dto.response.PurchaseHistory;

import java.util.List;
import java.util.StringJoiner;

public class OutputView {
    public void showPurchaseHistory(PurchaseHistory lottoTickets) {
        System.out.println(lottoTickets.getLottoBundle().size() + "개를 구매했습니다.");
        lottoTickets.getLottoBundle().forEach(bundle -> {
            printByBoxFormat(bundle.getNumbers());
        });
    }

    private void printByBoxFormat(List<Integer> numbers) {
        StringBuilder sb = new StringBuilder();
        StringJoiner sj = new StringJoiner(", ");
        sb.append("[");
        numbers.forEach(number -> sj.add(String.valueOf(number)));
        sb.append(sj.toString());
        sb.append("]");
        System.out.println(sb.toString());
    }
}
