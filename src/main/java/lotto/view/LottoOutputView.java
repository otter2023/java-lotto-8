package lotto.view;

import lotto.domain.Lottos;
import lotto.domain.Rank;

import java.util.Map;

public class LottoOutputView {

    public void printPurchasedLottos(Lottos purchasedLottos, int lottoCount) {
        System.out.println(lottoCount + "개를 구매했습니다.");
        purchasedLottos.printLottos();
    }

    public void printResult(Map<Rank, Integer> result) {
        System.out.println("---");

    }

}
