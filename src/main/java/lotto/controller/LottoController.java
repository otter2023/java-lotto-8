package lotto.controller;

import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.domain.Rank;
import lotto.service.LottoService;
import lotto.utils.LottoParser;
import lotto.validator.LottoValidator;
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
        Money money = new Money(amount);
        Lottos purchaselottos = service.createLottos(money);

        outputView.purchasedLottos(purchaselottos);

        Lotto winningLotto = LottoParser.stringToLotto(inputView.winningNumbers());
        int bonusNumber = Parser.stringToInt(inputView.bonusNumber());

        LottoValidator.validateBonusNotDuplicate(winningLotto, bonusNumber);

        Map<Rank, Integer> result = service.calculateRanks(purchaselottos, winningLotto, bonusNumber);
        int prize = service.calculatePrize(result);

        outputView.ranksResult(result, prize);
        outputView.totalRateOfPrize(money, prize);
    }
}
