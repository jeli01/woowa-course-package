package lotto;

import static lotto.Grade.FIFTH;
import static lotto.Grade.FIRST;
import static lotto.Grade.FOURTH;
import static lotto.Grade.NONE;
import static lotto.Grade.SECOND;
import static lotto.Grade.THIRD;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Calculator {
    static public Grade checkWinning(Lotto lotto, LottoDrawResult lottoDrawResult) {
        Lotto resultLottoNumbers = lottoDrawResult.getLotto();
        Integer resultBonus = lottoDrawResult.getBonus();

        Integer sameNumbersNumber = obtainEqualNumbersNumber(lotto, resultLottoNumbers);
        Boolean isBonusInLotto = isBonusInLotto(lotto, resultBonus);

        return obtainGrade(sameNumbersNumber, isBonusInLotto);
    }

    static private Integer obtainEqualNumbersNumber(Lotto lotto1, Lotto lotto2) {
        List<Integer> numbers1 = lotto1.getNumbers();
        List<Integer> numbers2 = lotto2.getNumbers();

        List<Integer> IntersectionNumbers = numbers1.stream()
                .filter(old -> numbers2.stream()
                        .anyMatch(Predicate.isEqual(old)))
                .collect(Collectors.toList());

        return IntersectionNumbers.size();
    }

    static private boolean isBonusInLotto(Lotto lotto, Integer bonus) {
        List<Integer> numbers = lotto.getNumbers();
        return numbers.contains(bonus);
    }

    static private Grade obtainGrade(Integer sameNumbersNumber, Boolean isBonusInLotto) {
        if (sameNumbersNumber == Lotto.size) {
            return FIRST;
        }
        if ((sameNumbersNumber == Lotto.size - 1) && isBonusInLotto == true) {
            return SECOND;
        }
        if (sameNumbersNumber == Lotto.size - 1) {
            return THIRD;
        }

        return getThirdAfterGrade(sameNumbersNumber);
    }

    private static Grade getThirdAfterGrade(Integer sameNumbersNumber) {
        if (sameNumbersNumber == Lotto.size - 2) {
            return FOURTH;
        }
        if (sameNumbersNumber == Lotto.size - 3) {
            return FIFTH;
        }

        return NONE;
    }

    static public Double returnRate(List<Lotto> lottos, LottoDrawResult lottoDrawResult) {
        Integer purchaseCnt = lottos.size();
        Integer purchaseMoney = purchaseCnt * Lotto.price;
        Integer winningMoney = 0;

        for (int i = 0; i < purchaseCnt; i++) {
            Grade grade = checkWinning(lottos.get(i), lottoDrawResult);
            winningMoney += obtainMoneyByGrade(grade);
        }

        return Double.valueOf(winningMoney) * 100 / purchaseMoney;
    }

    static private Integer obtainMoneyByGrade(Grade grade) {
        if (grade == FIRST) {
            return 2_000_000_000;
        }
        if (grade == SECOND) {
            return 30_000_000;
        }
        if (grade == THIRD) {
            return 1_500_000;
        }
        return getThirdAfterPrice(grade);
    }

    private static int getThirdAfterPrice(Grade grade) {
        if (grade == FOURTH) {
            return 50_000;
        }
        if (grade == FIFTH) {
            return 5_000;
        }
        return 0;
    }
}
