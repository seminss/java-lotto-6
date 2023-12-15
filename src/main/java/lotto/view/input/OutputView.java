package lotto.view.input;

import lotto.dto.response.PurchaseHistory;
import lotto.dto.response.Statistics;

import java.util.List;
import java.util.StringJoiner;

public class OutputView {
    public void showPurchaseHistory(PurchaseHistory lottoTickets) {
        System.out.printf("\n%d개를 구매했습니다.\n", lottoTickets.getLottoBundle().size());
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

    public void showStatistics(Statistics calculateResult) {
        System.out.println("\n당첨 통계");
        System.out.println("---");
        calculateResult.getRankResult().forEach((rank, count) -> {
            System.out.println(rank.getMatchCount() + "개 일치 (" + rank.getWinningMoney() + "원) - " + count + "개");
        });
        System.out.println("총 수익률은 " + calculateResult.getProfitRate() + "입니다.");
    }
}
