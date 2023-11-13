package christmas.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DiscountTest {
    @Test
    @DisplayName("2023.12.1에 1000원으로 이벤트가 시작된다.")
    void checkDDayStartDiscount() {
        OrderMenus orderMenus = new OrderMenus();
        orderMenus.addMenu(Menu.BARBECUE_RIBS, new MenuCount(10));
        orderMenus.addMenu(Menu.RED_WINE, new MenuCount(5));
        Order order = new Order(new Day(1), orderMenus);

        Assertions.assertThat(Discount.obtainChristmasDDayDiscount(order)).isEqualTo(1000);
    }

    @ParameterizedTest
    @CsvSource(value = {"25, 3400", "26, 0"})
    @DisplayName("100원씩 할인이 증가하며 2023.12.25에 이벤트가 종료된다.")
    void checkDDayLastDiscount(Integer day, Integer discount) {
        OrderMenus orderMenus = new OrderMenus();
        orderMenus.addMenu(Menu.BARBECUE_RIBS, new MenuCount(10));
        orderMenus.addMenu(Menu.RED_WINE, new MenuCount(5));
        Order order = new Order(new Day(day), orderMenus);

        Assertions.assertThat(Discount.obtainChristmasDDayDiscount(order)).isEqualTo(discount);
    }
}