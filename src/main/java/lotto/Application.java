package lotto;

import lotto.controller.LottoController;
import lotto.service.LottoService;
import lotto.view.LottoInputView;
import lotto.view.LottoOutputView;

public class Application {
    public static void main(String[] args) {
        LottoInputView inputView = new LottoInputView();
        LottoOutputView outputView = new LottoOutputView();
        LottoService service = new LottoService();
        LottoController controller = new LottoController(inputView, outputView, service);

        controller.run();
    }
}
