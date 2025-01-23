package christmas.domain;

import static christmas.domain.Constant.ARBITRARY_COUNT;
import static christmas.domain.Menu.BARBECUE_RIBS;
import static christmas.domain.Menu.CAESAR_SALAD;
import static christmas.domain.Menu.CHAMPAGNE;
import static christmas.domain.Menu.RED_WINE;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderMenusTest {
    private OrderMenus orderMenus = new OrderMenus();

    @BeforeEach
    void initialize() {
        orderMenus = new OrderMenus();
    }

    @Test
    @DisplayName("메뉴가 중복이 아닌 경우, 정상 동작한다.")
    void generateDay() {
        orderMenus.addMenu(BARBECUE_RIBS, new MenuCount(ARBITRARY_COUNT));

        assertThatCode(() -> {
            orderMenus.addMenu(CAESAR_SALAD, new MenuCount(ARBITRARY_COUNT));
        }).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("메뉴가 중복인 경우, 에러가 발생한다.")
    void validateDayRange() {
        orderMenus.addMenu(BARBECUE_RIBS, new MenuCount(ARBITRARY_COUNT));

        assertThatThrownBy(() -> {
            orderMenus.addMenu(BARBECUE_RIBS, new MenuCount(ARBITRARY_COUNT));
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("메뉴는 중복 주문할 수 없습니다.");
    }

    @Test
    @DisplayName("총 주문 메뉴의 수가 21개를 넘겼을 경우, 에러가 발생한다.")
    void checkTotalMenuCount() {
        orderMenus.addMenu(CHAMPAGNE, new MenuCount(10));

        assertThatCode(() -> {
            orderMenus.addMenu(RED_WINE, new MenuCount(12));
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("총 주문 메뉴를 21개를 넘길 수 없습니다.");
    }
}