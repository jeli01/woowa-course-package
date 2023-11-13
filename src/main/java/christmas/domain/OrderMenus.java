package christmas.domain;

import java.util.Map;

public class OrderMenus {
    private final Map<Menu, MenuCount> orderMenus;

    public OrderMenus(Map<Menu, MenuCount> orderMenus) {
        this.orderMenus = orderMenus;
    }
}