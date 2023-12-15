package lotto.view.input;

import lotto.dto.response.PurchaseHistory;
import lotto.dto.response.Statistics;
import lotto.model.Rank;

import java.text.DecimalFormat;
import java.util.List;
import java.util.StringJoiner;

public class OutputView {
    DecimalFormat priceFormat = new DecimalFormat("#,###");
    DecimalFormat profitFormat = new DecimalFormat("#.##");
    StringBuilder sb = new StringBuilder();
    StringJoiner sj = new StringJoiner(", ");

    public void showPurchaseHistory(PurchaseHistory lottoTickets) {
        System.out.printf("\n%d개를 구매했습니다.\n", lottoTickets.getLottoBundle().size());
        lottoTickets.getLottoBundle().forEach(bundle -> printByBoxFormat(bundle.getNumbers()));
    }

    private void printByBoxFormat(List<Integer> numbers) {
        sb = new StringBuilder();
        sb.append("[");
        numbers.forEach(number -> sj.add(String.valueOf(number)));
        sb.append(sj.toString());
        sb.append("]");
        System.out.println(sb.toString());
    }

    public void showStatistics(Statistics calculateResult) {
        sb = new StringBuilder();
        sb.append("\n당첨 통계\n---\n");
        for (Rank rank : Rank.values()) {
            if (rank == Rank.MISS) {
                continue;
            }
            if (rank == Rank.SECOND) {
                sb.append(formatSecondRankMessage(rank, rank.getMatchCount(), rank.getWinningMoney()));
                continue;
            }
            sb.append(formatDefaultRankMessage(rank, rank.getMatchCount(), rank.getWinningMoney()));
        }
        sb.append(formatProfitRateMessage(calculateResult));
        System.out.println(sb.toString());
    }

    private String formatProfitRateMessage(Statistics calculateResult) {
        return String.format("총 수익률은 %s%%입니다.", profitFormat.format(calculateResult.getProfitRate()));
    }

    private String formatDefaultRankMessage(Rank rank, int count, int winningMoney) {
        String formattedWinningMoney = priceFormat.format(winningMoney);
        return String.format("%d개 일치 (%s원) - %d개\n", rank.getMatchCount(), formattedWinningMoney, count);
    }

    private String formatSecondRankMessage(Rank rank, int count, int winningMoney) {
        String formattedWinningMoney = priceFormat.format(winningMoney);
        return String.format("%d개, 보너스 볼 일치 (%s원) - %d개\n", rank.getMatchCount(), formattedWinningMoney, count);
    }

}
