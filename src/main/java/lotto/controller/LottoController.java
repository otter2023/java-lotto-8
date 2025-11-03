package lotto.controller;

import lotto.domain.*;
import lotto.service.LottoService;
import lotto.utils.LottoParser;
import lotto.validator.LottoValidator;
import lotto.view.LottoInputView;
import lotto.view.LottoOutputView;
import utils.Parser;

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
        Money money = readAmount();

        Lottos purchasedLottos = service.createLottos(money);
        outputView.purchasedLottos(purchasedLottos);

        Lotto winningLotto = readWinningLotto();

        int bonusNumber = readBonusNumber(winningLotto);

        Map<Rank, Integer> result = service.calculateRanks(purchasedLottos, winningLotto, bonusNumber);
        int prize = service.calculatePrize(result);

        outputView.ranksResult(result, prize);
        outputView.totalRateOfPrize(money, prize);
    }

    private Money readAmount() {
        while (true) {
            try {
                String input = inputView.amount();
                int value = Parser.stringToInt(input);
                return new Money(value);
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private Lotto readWinningLotto() {
        while (true) {
            try {
                String input = inputView.winningNumbers();
                return LottoParser.stringToLotto(input);
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
    private int readBonusNumber(Lotto winningLotto) {
        while (true) {
            try {
                String input = inputView.bonusNumber();
                int bonus = Parser.stringToInt(input);
                LottoValidator.validateBonusNotDuplicate(winningLotto, bonus);
                return bonus;
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
