package christmas.domain;

import java.util.HashMap;
import java.util.Map;

public class OrderMenus {
    public static final String MENU_DUPLICATE_ERROR = "메뉴는 중복 주문할 수 없습니다.";

    private final Map<Menu, MenuCount> orderMenus;

    public OrderMenus() {
        orderMenus = new HashMap<>();
    }

    public void addMenu(Menu menu, MenuCount count) {
        validateMenuDuplicate(menu);
        orderMenus.put(menu, count);
    }

    private void validateMenuDuplicate(Menu menu) {
        if (orderMenus.containsKey(menu)) {
            throw new IllegalArgumentException(MENU_DUPLICATE_ERROR);
        }
    }
}