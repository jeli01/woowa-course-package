package christmas.domain;

public class Order {
    private static final String DRINK_ONLY_ERROR = "음료만 주문할 수는 없습니다.";
    private static final String MAX_ORDER_COUNT_ERROR = "총 주문 메뉴를 21개를 넘길 수 없습니다.";
    private static final Integer MAX_ORDER_COUNT = 21;

    private final Day visitDay;
    private final OrderMenus orderMenus;

    public Order(Day visitDay, OrderMenus orderMenus) {
        validateMenusDiversity(orderMenus);
        this.visitDay = visitDay;
        this.orderMenus = orderMenus;
    }

    private void validateMenusDiversity(OrderMenus orderMenus) {
        if (orderMenus.obtainMenuTotalCount() > MAX_ORDER_COUNT) {
            throw new IllegalArgumentException(MAX_ORDER_COUNT_ERROR);
        }
        if (orderMenus.isOnlyDrink()) {
            throw new IllegalArgumentException(DRINK_ONLY_ERROR);
        }
    }

    public boolean isBiggerDay(Day day) {
        return visitDay.isBigger(day);
    }

    public Integer obtainDayDifference(Day day) {
        return visitDay.obtainDifference(day);
    }

    public boolean isWeekendDay() {
        return visitDay.isWeekend();
    }

    public Integer obtainMenuCategoryCount(MenuCategory menuCategory) {
        return orderMenus.obtainCategoryCount(menuCategory);
    }

    public boolean isStarDay() {
        return visitDay.isStar();
    }

    public Integer obtainTotalPrice() {
        return orderMenus.obtainTotalPrice();
    }
}