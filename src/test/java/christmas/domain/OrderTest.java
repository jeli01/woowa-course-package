package christmas.domain;

import static christmas.domain.Order.DRINK_ONLY_ERROR;
import static christmas.domain.Order.MAX_ORDER_COUNT_ERROR;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderTest {
    @Test
    @DisplayName("음료만 주문을 하지 않으면, 정상 동작한다.")
    void generateOrder() {
        OrderMenus orderMenus = new OrderMenus();
        orderMenus.addMenu(Menu.CHAMPAGNE, new MenuCount(3));
        orderMenus.addMenu(Menu.BARBECUE_RIBS, new MenuCount(5));

        Assertions.assertThatCode(() -> {
            new Order(new VisitDay(10), orderMenus);
        }).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("음료만 주문했을 경우, 에러가 발생한다.")
    void checkOnlyDrink() {
        OrderMenus orderMenus = new OrderMenus();
        orderMenus.addMenu(Menu.CHAMPAGNE, new MenuCount(3));
        orderMenus.addMenu(Menu.RED_WINE, new MenuCount(5));

        Assertions.assertThatCode(() -> {
        }).hasMessageContaining(DRINK_ONLY_ERROR);
    }

    @Test
    @DisplayName("총 주문 메뉴의 수가 21개를 넘겼을 경우, 에러가 발생한다.")
    void checkTotalMenuCount() {
        OrderMenus orderMenus = new OrderMenus();
        orderMenus.addMenu(Menu.CHAMPAGNE, new MenuCount(10));
        orderMenus.addMenu(Menu.RED_WINE, new MenuCount(15));

        Assertions.assertThatCode(() -> {
            new Order(new VisitDay(10), orderMenus);
        }).hasMessageContaining(MAX_ORDER_COUNT_ERROR);
    }
}