package christmas.domain;

import java.util.Map;

public class OrderMenus {
    private final Map<Menu, Integer> orderMenus;

    public OrderMenus(Map<Menu, Integer> orderMenus) {
        this.orderMenus = orderMenus;
    }
}