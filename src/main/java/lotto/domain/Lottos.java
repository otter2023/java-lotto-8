package lotto.domain;

import lotto.service.LottoGenerator;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Lottos {
    private final List<Lotto> lottos;
    private int count;

    public Lottos(int count) {
        this.lottos = createLottos(count);
        this.count = count;
    }

    public List<Lotto> createLottos(int count) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(LottoGenerator.generate());
        }

        return lottos;
    }

    public void printLottos() {
        lottos.forEach(System.out::println);
        /* 아래와 동일한 코드
        for (Lotto lotto : lottos) {
           System.out.println(lotto);
        }
         */
    }

    // TODO : 필요하다면 리팩토링
    public Map<Rank, Integer> calculateRanks(Lotto winningLotto, int bonusNumber) {
        Map<Rank, Integer> results = new EnumMap<>(Rank.class);

        for (Lotto lotto : lottos) {
            Rank rank = lotto.calculateRank(winningLotto, bonusNumber);
            results.put(rank, results.getOrDefault(rank, 0) + 1);
        }

        return results;
    }

}
