package christmas.domain;

public class Order {
    public static final String DRINK_ONLY_ERROR = "음료만 주문할 수는 없습니다.";
    public static final String MAX_ORDER_COUNT_ERROR = "총 주문 메뉴를 21개를 넘길 수 없습니다.";
    private static final Integer MAX_ORDER_COUNT = 21;

    private final VisitDay visitDay;
    private final OrderMenus orderMenus;

    public Order(VisitDay visitDay, OrderMenus orderMenus) {
        validateMenusDiversity(orderMenus);
        this.visitDay = visitDay;
        this.orderMenus = orderMenus;
    }

    private void validateMenusDiversity(OrderMenus orderMenus) {
        if (orderMenus.obtainOrderMenuTotalCount() > MAX_ORDER_COUNT) {
            throw new IllegalArgumentException(MAX_ORDER_COUNT_ERROR);
        }
        if (orderMenus.isOnlyDrink()) {
            throw new IllegalArgumentException(DRINK_ONLY_ERROR);
        }
    }
}