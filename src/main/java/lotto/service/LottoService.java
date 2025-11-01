package lotto.service;

import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.domain.Rank;

import java.util.Map;

public class LottoService {

    public Lottos createLottos(Money money) {
        int count = money.getValue() / 1000;
        return new Lottos(count);
    }

    public Map<Rank, Integer> calculateRanks(Lottos lottos, Lotto winningLotto, int bonusNumber) {
        return lottos.calculateRanks(winningLotto, bonusNumber);
    }

    public int calculatePrize(Map<Rank, Integer> result) {
        int prize = 0;
        for (Rank rank : result.keySet()) {
            int count = result.get(rank);
            prize += rank.getPrize() * count;
        }
        return prize;
    }
}
