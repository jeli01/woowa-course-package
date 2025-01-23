package christmas.domain;

import static christmas.domain.Badge.SANTA;
import static christmas.domain.Badge.STAR;
import static christmas.domain.Badge.TREE;
import static christmas.domain.Badge.obtainBadge;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BadgeTest {
    @Test
    @DisplayName("총 혜택 금액이 5천 원 미만이면 지급하지 않는다.")
    void obtainBadgeLess5000Price() {
        final Integer TOTAL_BENEFIT_PRICE = 4_999;
        assertThat(obtainBadge(TOTAL_BENEFIT_PRICE)).isEqualTo(null);
    }

    @Test
    @DisplayName("총 혜택 금액이 5천 원 이상이면 별을 지급한다.")
    void obtainBadgeMore5000Price() {
        final Integer TOTAL_BENEFIT_PRICE = 5_000;
        assertThat(obtainBadge(TOTAL_BENEFIT_PRICE)).isEqualTo(STAR);
    }

    @Test
    @DisplayName("총 혜택 금액이 1만 원 이상이면 트리를 지급한다.")
    void obtainBadgeMore10000Price() {
        final Integer TOTAL_BENEFIT_PRICE = 10_000;
        assertThat(obtainBadge(TOTAL_BENEFIT_PRICE)).isEqualTo(TREE);
    }

    @Test
    @DisplayName("총 혜택 금액이 2만 원 이상이면 산타를 지급한다.")
    void obtainBadgeMore20000Price() {
        final Integer TOTAL_BENEFIT_PRICE = 20_000;
        assertThat(obtainBadge(TOTAL_BENEFIT_PRICE)).isEqualTo(SANTA);
    }
}