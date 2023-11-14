package christmas.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class OrderMenus {
    private static final String DUPLICATE_ERROR = "메뉴는 중복 주문할 수 없습니다.";

    private final Map<Menu, MenuCount> orderMenus;

    public OrderMenus() {
        orderMenus = new HashMap<>();
    }

    public void addMenu(Menu menu, MenuCount count) {
        validateDuplicate(menu);
        orderMenus.put(menu, count);
    }

    private void validateDuplicate(Menu menu) {
        if (orderMenus.containsKey(menu)) {
            throw new IllegalArgumentException(DUPLICATE_ERROR);
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
            totalCount = menuCount.obtainSumWithCount(totalCount);
        }

        return totalCount;
    }

    public Integer obtainCategoryCount(MenuCategory menuCategory) {
        Integer count = 0;
        Set<Menu> menus = orderMenus.keySet();
        for (Menu menu : menus) {
            if (menu.isCategory(menuCategory)) {
                MenuCount menuCount = orderMenus.get(menu);
                count = menuCount.obtainSumWithCount(count);
            }
        }
        return count;
    }

    public Integer obtainTotalPrice() {
        Integer totalPrice = 0;
        Set<Menu> menus = orderMenus.keySet();
        for (Menu menu : menus) {
            MenuCount menuCount = orderMenus.get(menu);
            Integer oneKindMenuPrice = menu.obtainPriceMultiplyCount(menuCount);
            totalPrice += oneKindMenuPrice;
        }
        return totalPrice;
    }

    public Map<Menu, MenuCount> getOrderMenus() {
        return orderMenus;
    }
}