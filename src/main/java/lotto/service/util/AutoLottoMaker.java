package lotto.service.util;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static lotto.model.Ticket.*;

public class AutoLottoMaker implements LottoMaker {
    @Override
    public List<Integer> createLottoNumbers() {
        List<Integer> randomNumbers = Randoms.pickUniqueNumbersInRange(LOTTO_NUMBER_MIN.getValue(),
                LOTTO_NUMBER_MAX.getValue(), LOTTO_NUMBER_COUNT.getValue());
        List<Integer> sortedNumbers = new ArrayList<>(randomNumbers);
        Collections.sort(sortedNumbers);
        return Collections.unmodifiableList(randomNumbers);
    }
}
