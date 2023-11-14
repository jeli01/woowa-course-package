package christmas.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BadgeTest {
    @Test
    @DisplayName("총 혜택 금액이 5천 원 미만이면 지급하지 않는다.")
    void obtainBadgeLess5000Price() {
        Integer totalBenefitPrice = 4_999;
        Assertions.assertThat(Badge.obtainBadge(totalBenefitPrice)).isEqualTo(null);
    }

    @Test
    @DisplayName("총 혜택 금액이 5천 원 이상이면 별을 지급한다.")
    void obtainBadgeMore5000Price() {
        Integer totalBenefitPrice = 5_000;
        Assertions.assertThat(Badge.obtainBadge(totalBenefitPrice)).isEqualTo(Badge.STAR);
    }

    @Test
    @DisplayName("총 혜택 금액이 1만 원 이상이면 트리를 지급한다.")
    void obtainBadgeMore10000Price() {
        Integer totalBenefitPrice = 10_000;
        Assertions.assertThat(Badge.obtainBadge(totalBenefitPrice)).isEqualTo(Badge.TREE);
    }

    @Test
    @DisplayName("총 혜택 금액이 2만 원 이상이면 산타를 지급한다.")
    void obtainBadgeMore20000Price() {
        Integer totalBenefitPrice = 20_000;
        Assertions.assertThat(Badge.obtainBadge(totalBenefitPrice)).isEqualTo(Badge.SANTA);
    }
}