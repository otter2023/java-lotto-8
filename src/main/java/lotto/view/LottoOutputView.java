package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.domain.Rank;
import utils.Parser;

import java.util.Map;

public class LottoOutputView {

    public void purchasedLottos(Lottos purchasedLottos) {
        System.out.println();
        System.out.println(purchasedLottos.getCount() + "개를 구매했습니다.");

        for (Lotto lotto : purchasedLottos.getLottos()) {
            System.out.println(lotto.getNumbers());
        }
    }

    public void ranksResult(Map<Rank, Integer> result, int prize) {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---");
        System.out.println("3개 일치 (5,000원) - " + result.getOrDefault(Rank.FIFTH, 0) + "개");
        System.out.println("4개 일치 (50,000원) - " + result.getOrDefault(Rank.FOURTH, 0) + "개");
        System.out.println("5개 일치 (1,500,000원) - " + result.getOrDefault(Rank.THIRD, 0) + "개");
        System.out.println("5개 일치, 보너스 볼 일치 (30,000,000원) - " + result.getOrDefault(Rank.SECOND, 0) + "개");
        System.out.println("6개 일치 (2,000,000,000원) - " + result.getOrDefault(Rank.FIRST, 0) + "개");
    }

    public void totalRateOfPrize(Money money, int prize) {
        double result = Parser.round(money.calculateRateOfPrize(prize));
        System.out.println("총 수익률은 " + result + "%입니다.");
    }
}
