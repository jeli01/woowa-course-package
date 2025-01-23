package christmas.domain;

import static christmas.domain.Constant.ARBITRARY_COUNT;
import static christmas.domain.Constant.ARBITRARY_DAY;
import static christmas.domain.Menu.BARBECUE_RIBS;
import static christmas.domain.Menu.CHAMPAGNE;
import static christmas.domain.Menu.RED_WINE;
import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderTest {
    private OrderMenus orderMenus = new OrderMenus();

    @BeforeEach
    void initialize() {
        orderMenus = new OrderMenus();
    }

    @Test
    @DisplayName("음료만 주문을 하지 않으면, 정상 동작한다.")
    void generateOrder() {
        orderMenus.addMenu(CHAMPAGNE, new MenuCount(ARBITRARY_COUNT));
        orderMenus.addMenu(BARBECUE_RIBS, new MenuCount(ARBITRARY_COUNT));

        assertThatCode(() -> {
            new Order(new Day(ARBITRARY_DAY), orderMenus);
        }).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("음료만 주문했을 경우, 에러가 발생한다.")
    void checkOnlyDrink() {
        orderMenus.addMenu(CHAMPAGNE, new MenuCount(ARBITRARY_COUNT));
        orderMenus.addMenu(RED_WINE, new MenuCount(ARBITRARY_COUNT));

        assertThatCode(() -> {
            new Order(new Day(ARBITRARY_DAY), orderMenus);
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("음료만 주문할 수는 없습니다.");
    }
}