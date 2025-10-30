package lotto.service;

import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.Rank;

import java.util.Map;

public class LottoService {

    public Lottos createLottos(int amount) {
        return new Lottos(amount);
    }

    public Map<Rank, Integer> calculateRanks(Lottos lottos, Lotto winningLotto, int bonusNumber) {
        return lottos.calculateRanks(winningLotto, bonusNumber);
    }
}
