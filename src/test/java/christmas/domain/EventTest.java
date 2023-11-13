package christmas.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class EventTest {
    @Test
    @DisplayName("2023.12.1에 1000원으로 이벤트가 시작된다.")
    void checkDDayStartDiscount() {
        OrderMenus orderMenus = new OrderMenus();
        orderMenus.addMenu(Menu.BARBECUE_RIBS, new MenuCount(10));
        orderMenus.addMenu(Menu.RED_WINE, new MenuCount(5));
        Order order = new Order(new Day(1), orderMenus);

        Assertions.assertThat(Event.obtainChristmasDDayDiscount(order)).isEqualTo(1000);
    }

    @ParameterizedTest
    @CsvSource(value = {"25, 3400", "26, 0"})
    @DisplayName("100원씩 할인이 증가하며 2023.12.25에 이벤트가 종료된다.")
    void checkDDayLastDiscount(Integer day, Integer discount) {
        OrderMenus orderMenus = new OrderMenus();
        orderMenus.addMenu(Menu.BARBECUE_RIBS, new MenuCount(10));
        orderMenus.addMenu(Menu.RED_WINE, new MenuCount(5));
        Order order = new Order(new Day(day), orderMenus);

        Assertions.assertThat(Event.obtainChristmasDDayDiscount(order)).isEqualTo(discount);
    }

    @Test
    @DisplayName("평일에는 디저트 메뉴를 메뉴 1개당 2,023원 할인한다.")
    void checkWeekdayDiscount() {
        final OrderMenus orderMenus = new OrderMenus();
        orderMenus.addMenu(Menu.ICE_CREAM, new MenuCount(10));
        orderMenus.addMenu(Menu.RED_WINE, new MenuCount(5));
        final Order order = new Order(new Day(3), orderMenus);

        Assertions.assertThat(Event.obtainWeekKindDiscount(order)).isEqualTo(2_023 * 10);
    }

    @Test
    @DisplayName("주말에는 메인 메뉴를 메뉴 1개당 2,023원 할인한다.")
    void checkWeekendDiscount() {
        final OrderMenus orderMenus = new OrderMenus();
        orderMenus.addMenu(Menu.BARBECUE_RIBS, new MenuCount(7));
        orderMenus.addMenu(Menu.RED_WINE, new MenuCount(5));
        final Order order = new Order(new Day(1), orderMenus);

        Assertions.assertThat(Event.obtainWeekKindDiscount(order)).isEqualTo(2_023 * 7);
    }

    @Test
    @DisplayName("이벤트 달력에 별이 있으면 총주문 금액에서 1,000원 할인한다.")
    void checkStarDayDiscount() {
        final OrderMenus orderMenus = new OrderMenus();
        orderMenus.addMenu(Menu.BARBECUE_RIBS, new MenuCount(7));
        final Order order = new Order(new Day(3), orderMenus);

        Assertions.assertThat(Event.obtainStarDiscount(order)).isEqualTo(1000);
    }

    @Test
    @DisplayName("할인 전 총주문 금액이 12만 원 이상일 때, 샴페인 1개 증정한다.")
    void checkChampagnePresent() {
        Assertions.assertThat(Event.isPossibleGetChampagne(120_000)).isEqualTo(true);
    }

    @Test
    @DisplayName("할인 전 총주문 금액이 12만 원 미만일 때, 샴페인을 증정하지 않는다.")
    void checkNoChampagnePresent() {
        Assertions.assertThat(Event.isPossibleGetChampagne(119_999)).isEqualTo(false);
    }

    @ParameterizedTest
    @CsvSource(value = {"9_999, false", "10_000, true"})
    @DisplayName("총 주문 금액 10,000원 이상부터 위의 이벤트가 적용된다.")
    void check(Integer totalOrderPrice, Boolean applyResult) {
        Assertions.assertThat(EventApplyer.isPossibleEvent(totalOrderPrice)).isEqualTo(applyResult);

    }
}