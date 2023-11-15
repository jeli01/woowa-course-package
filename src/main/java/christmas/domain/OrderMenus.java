package christmas.domain;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class OrderMenus {
    private static final String DUPLICATE_ERROR = "메뉴는 중복 주문할 수 없습니다.";
    private static final String MAX_ORDER_COUNT_ERROR = "총 주문 메뉴를 21개를 넘길 수 없습니다.";
    private static final Integer MAX_ORDER_COUNT = 21;

    private final Map<Menu, MenuCount> orderMenus;

    public OrderMenus() {
        orderMenus = new HashMap<>();
    }

    public void addMenu(Menu menu, MenuCount count) {
        validateDuplicate(menu);
        validateMenuTotalCount(count);
        orderMenus.put(menu, count);
    }

    private void validateDuplicate(Menu menu) {
        if (orderMenus.containsKey(menu)) {
            throw new IllegalArgumentException(DUPLICATE_ERROR);
        }
    }

    private void validateMenuTotalCount(MenuCount count) {
        Integer totalCount = obtainMenuTotalCount() + count.getRawCount();
        if (MAX_ORDER_COUNT < totalCount) {
            throw new IllegalArgumentException(MAX_ORDER_COUNT_ERROR);
        }
    }

    public boolean isOnlyDrink() {
        Set<Menu> menus = orderMenus.keySet();
        for (Menu menu : menus) {
            if (!menu.isDrink()) {
                return false;
            }
        }
        return true;
    }

    public Integer obtainMenuTotalCount() {
        Collection<MenuCount> values = orderMenus.values();
        Integer totalCount = 0;
        for (MenuCount menuCount : values) {
            totalCount += menuCount.getRawCount();
        }

        return totalCount;
    }

    public Integer obtainCategoryCount(MenuCategory menuCategory) {
        Integer count = 0;
        Set<Menu> menus = orderMenus.keySet();
        for (Menu menu : menus) {
            if (menu.isCategory(menuCategory)) {
                MenuCount menuCount = orderMenus.get(menu);
                count += menuCount.getRawCount();
            }
        }
        return count;
    }

    public Integer obtainTotalPrice() {
        Integer totalPrice = 0;
        Set<Menu> menus = orderMenus.keySet();
        for (Menu menu : menus) {
            MenuCount menuCount = orderMenus.get(menu);
            Integer oneKindMenuPrice = menu.getPrice() * menuCount.getRawCount();
            totalPrice += oneKindMenuPrice;
        }
        return totalPrice;
    }

    public Map<Menu, MenuCount> getRawOrderMenus() {
        return Collections.unmodifiableMap(orderMenus);
    }
}