package christmas.domain;

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
            new Order(new Day(10), orderMenus);
        }).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("음료만 주문했을 경우, 에러가 발생한다.")
    void checkOnlyDrink() {
        OrderMenus orderMenus = new OrderMenus();
        orderMenus.addMenu(Menu.CHAMPAGNE, new MenuCount(3));
        orderMenus.addMenu(Menu.RED_WINE, new MenuCount(5));

        Assertions.assertThatCode(() -> {
            new Order(new Day(10), orderMenus);
        }).hasMessageContaining("음료만 주문할 수는 없습니다.");
    }
}