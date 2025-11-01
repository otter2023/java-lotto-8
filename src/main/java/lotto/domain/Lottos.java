package lotto.domain;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(int count) {
        this.lottos = createLottos(count);
    }

    public List<Lotto> createLottos(int count) {
        List<Lotto> lottos = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            lottos.add(LottoGenerator.generate());
        }

        return lottos;
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

    public int getCount() {
        return lottos.size();
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}
