package christmas.domain;

public class Order {
    private final VisitDay visitDay;
    private final OrderMenus orderMenus;

    public Order(VisitDay visitDay, OrderMenus orderMenus) {
        this.visitDay = visitDay;
        this.orderMenus = orderMenus;
    }
}