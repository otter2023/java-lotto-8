package lotto.controller;

import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.Rank;
import lotto.service.LottoService;
import lotto.utils.LottoParser;
import utils.Parser;
import lotto.view.LottoInputView;
import lotto.view.LottoOutputView;

import java.util.Map;

public class LottoController {

    private final LottoService service;
    private final LottoInputView inputView;
    private final LottoOutputView outputView;

    public LottoController(LottoInputView inputView, LottoOutputView outputView, LottoService service) {
        this.service = service;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        int amount = Parser.stringToInt(inputView.amount());

        Lottos purchaselottos = service.createLottos(amount);

        outputView.printPurchasedLottos(purchaselottos, amount);

        Lotto winningLotto = LottoParser.stringToLotto(inputView.winningNumbers());
        int bonusNumber = Parser.stringToInt(inputView.bonusNumber());

        Map<Rank, Integer> result = service.calculateRanks(purchaselottos, winningLotto, bonusNumber);

        outputView.printResult(result);
    }

}
