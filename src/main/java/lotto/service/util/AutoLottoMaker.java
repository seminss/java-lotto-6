package lotto.service.util;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.List;

import static lotto.model.Ticket.*;

public class AutoLottoMaker implements LottoMaker {
    @Override
    public List<Integer> createLottoNumbers() {
        return Randoms.pickUniqueNumbersInRange(LOTTO_NUMBER_MIN.getValue(),
                LOTTO_NUMBER_MAX.getValue(), LOTTO_NUMBER_COUNT.getValue());
    }
}
