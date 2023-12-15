package lotto.service;

import lotto.model.Lotto;
import lotto.model.LottoRepository;
import lotto.service.util.LottoMaker;

public class LottoService {
    private final LottoMaker lottoMaker;

    public LottoService(LottoMaker lottoMaker) {
        this.lottoMaker = lottoMaker;
    }

    public void createLottoBundle(int lottoCount){
        for(int i = 0; i < lottoCount; i++){
            createLotto();
        }
    }

    private void createLotto() {
        Lotto lotto = Lotto.of(lottoMaker.createLottoNumbers());
        LottoRepository.addLotto(lotto);
    }
}
