package christmas.domain;

public class Order {
    public static final String DRINK_ONLY_ERROR = "음료만 주문할 수는 없습니다.";

    private final VisitDay visitDay;
    private final OrderMenus orderMenus;

    public Order(VisitDay visitDay, OrderMenus orderMenus) {
        validateMenusDiversity(orderMenus);
        this.visitDay = visitDay;
        this.orderMenus = orderMenus;
    }

    private void validateMenusDiversity(OrderMenus orderMenus) {
        if (orderMenus.isOnlyDrink()) {
            throw new IllegalArgumentException(DRINK_ONLY_ERROR);
        }
    }
}