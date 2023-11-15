package christmas.domain;

import static christmas.domain.Constant.ARBITRARY_COUNT;
import static christmas.domain.Constant.ARBITRARY_DAY;
import static christmas.domain.Constant.STAR_DAY;
import static christmas.domain.Constant.WEEKDAY;
import static christmas.domain.Constant.WEEKEND;
import static christmas.domain.Constant.WEEK_KIND_DISCOUNT_UNIT;
import static christmas.domain.Event.getPossibleChampagneCount;
import static christmas.domain.Event.obtainChristmasDDayDiscount;
import static christmas.domain.Event.obtainStarDiscount;
import static christmas.domain.Event.obtainTotalDiscount;
import static christmas.domain.Event.obtainWeekKindDiscount;
import static christmas.domain.EventApplier.isPossibleDiscountEvent;
import static christmas.domain.Menu.BARBECUE_RIBS;
import static christmas.domain.Menu.CHOCOLATE_CAKE;
import static christmas.domain.Menu.ICE_CREAM;
import static christmas.domain.Menu.RED_WINE;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class EventTest {
    private OrderMenus orderMenus;

    @BeforeEach
    void initialize() {
        orderMenus = new OrderMenus();
    }

    @Test
    @DisplayName("2023.12.1에 1000원으로 이벤트가 시작된다.")
    void checkDDayStartDiscount() {
        orderMenus.addMenu(BARBECUE_RIBS, new MenuCount(ARBITRARY_COUNT));
        orderMenus.addMenu(RED_WINE, new MenuCount(ARBITRARY_COUNT));
        Order order = new Order(new Day(1), orderMenus);

        assertThat(obtainChristmasDDayDiscount(order)).isEqualTo(1000);
    }

    @ParameterizedTest
    @CsvSource(value = {"25, 3400", "26, 0"})
    @DisplayName("100원씩 할인이 증가하며 2023.12.25에 이벤트가 종료된다.")
    void checkDDayLastDiscount(Integer day, Integer discount) {
        orderMenus.addMenu(BARBECUE_RIBS, new MenuCount(ARBITRARY_COUNT));
        orderMenus.addMenu(RED_WINE, new MenuCount(ARBITRARY_COUNT));
        Order order = new Order(new Day(day), orderMenus);

        assertThat(obtainChristmasDDayDiscount(order)).isEqualTo(discount);
    }

    @Test
    @DisplayName("평일에는 디저트 메뉴를 메뉴 1개당 2,023원 할인한다.")
    void checkWeekdayDiscount() {
        orderMenus.addMenu(ICE_CREAM, new MenuCount(ARBITRARY_COUNT));
        orderMenus.addMenu(RED_WINE, new MenuCount(ARBITRARY_COUNT));
        Order order = new Order(new Day(WEEKDAY), orderMenus);

        assertThat(obtainWeekKindDiscount(order)).isEqualTo(WEEK_KIND_DISCOUNT_UNIT * ARBITRARY_COUNT);
    }

    @Test
    @DisplayName("주말에는 메인 메뉴를 메뉴 1개당 2,023원 할인한다.")
    void checkWeekendDiscount() {
        orderMenus.addMenu(BARBECUE_RIBS, new MenuCount(ARBITRARY_COUNT));
        orderMenus.addMenu(RED_WINE, new MenuCount(ARBITRARY_COUNT));
        Order order = new Order(new Day(WEEKEND), orderMenus);

        assertThat(obtainWeekKindDiscount(order)).isEqualTo(WEEK_KIND_DISCOUNT_UNIT * ARBITRARY_COUNT);
    }

    @Test
    @DisplayName("이벤트 달력에 별이 있으면 총주문 금액에서 1,000원 할인한다.")
    void checkStarDayDiscount() {
        orderMenus.addMenu(BARBECUE_RIBS, new MenuCount(ARBITRARY_COUNT));
        Order order = new Order(new Day(STAR_DAY), orderMenus);

        assertThat(obtainStarDiscount(order)).isEqualTo(1000);
    }

    @Test
    @DisplayName("할인 전 총주문 금액이 12만 원 이상일 때, 샴페인 1개 증정한다.")
    void checkChampagnePresent() {
        assertThat(getPossibleChampagneCount(120_000)).isEqualTo(1);
    }

    @Test
    @DisplayName("할인 전 총주문 금액이 12만 원 미만일 때, 샴페인을 증정하지 않는다.")
    void checkNoChampagnePresent() {
        assertThat(getPossibleChampagneCount(119_999)).isEqualTo(0);
    }

    @ParameterizedTest
    @CsvSource(value = {"9_999, false", "10_000, true"})
    @DisplayName("총 주문 금액 10,000원 이상부터 위의 이벤트가 적용된다.")
    void checkMore10_000PossibleEvent(Integer totalOrderPrice, Boolean applyResult) {
        assertThat(isPossibleDiscountEvent(totalOrderPrice)).isEqualTo(applyResult);

    }

    @Test
    @DisplayName("총 할인 금액을 가져온다.")
    void checkDiscountIsLessThanPayment() {
        orderMenus.addMenu(CHOCOLATE_CAKE, new MenuCount(ARBITRARY_COUNT));
        Order order = new Order(new Day(ARBITRARY_DAY), orderMenus);

        Integer christmasDDayDiscount = obtainChristmasDDayDiscount(order);
        Integer starDiscount = obtainStarDiscount(order);
        Integer weekKindDiscount = obtainWeekKindDiscount(order);
        assertThat(obtainTotalDiscount(order))
                .isEqualTo(christmasDDayDiscount + starDiscount + weekKindDiscount);
    }
}