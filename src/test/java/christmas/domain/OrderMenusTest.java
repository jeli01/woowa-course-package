package christmas.domain;

import static christmas.domain.OrderMenus.MENU_DUPLICATE_ERROR;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderMenusTest {
    @Test
    @DisplayName("메뉴가 중복이 아닌 경우, 정상 동작한다.")
    void generateDay() {
        OrderMenus orderMenus = new OrderMenus();
        orderMenus.addMenu(Menu.BARBECUE_RIBS, new MenuCount(3));

        Assertions.assertThatCode(() -> {
            orderMenus.addMenu(Menu.CAESAR_SALAD, new MenuCount(5));
        }).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("메뉴가 중복인 경우, 에러가 발생한다.")
    void validateDayRange() {
        OrderMenus orderMenus = new OrderMenus();
        orderMenus.addMenu(Menu.BARBECUE_RIBS, new MenuCount(3));

        Assertions.assertThatThrownBy(() -> {
            orderMenus.addMenu(Menu.BARBECUE_RIBS, new MenuCount(5));
        }).hasMessageContaining(MENU_DUPLICATE_ERROR);
    }
}