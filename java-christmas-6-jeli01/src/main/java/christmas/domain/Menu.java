package christmas.domain;

import static christmas.domain.MenuCategory.APPETIZER;
import static christmas.domain.MenuCategory.DESSERT;
import static christmas.domain.MenuCategory.DRINK;
import static christmas.domain.MenuCategory.MAIN;

public enum Menu {
    BUTTON_MUSHROOM_SOUP(APPETIZER, "양송이수프", 6_000),
    TAPAS(APPETIZER, "타파스", 5_500),
    CAESAR_SALAD(APPETIZER, "시저샐러드", 8_000),
    T_BONE_STEAK(MAIN, "티본스테이크", 55_000),
    BARBECUE_RIBS(MAIN, "바비큐립", 54_000),
    SEAFOOD_PASTA(MAIN, "해산물파스타", 35_000),
    CHRISTMAS_PASTA(MAIN, "크리스마스파스타", 25_000),
    CHOCOLATE_CAKE(DESSERT, "초코케이크", 15_000),
    ICE_CREAM(DESSERT, "아이스크림", 5_000),
    ZERO_COKE(DRINK, "제로콜라", 3_000),
    RED_WINE(DRINK, "레드와인", 60_000),
    CHAMPAGNE(DRINK, "샴페인", 25_000);

    private static final String NOT_EXISTS_ERROR = "해당 문자열은 메뉴의 종류가 아닙니다.";

    private final MenuCategory category;
    private final String name;
    private final Integer price;

    Menu(MenuCategory category, String name, Integer price) {
        this.category = category;
        this.name = name;
        this.price = price;
    }

    public static Menu translate(String input) {
        Menu[] values = Menu.values();
        for (Menu value : values) {
            if (input.equals(value.name)) {
                return value;
            }
        }
        throw new IllegalArgumentException(NOT_EXISTS_ERROR);
    }

    public boolean isDrink() {
        if (category == DRINK) {
            return true;
        }
        return false;
    }

    public boolean isCategory(MenuCategory menuCategory) {
        if (category == menuCategory) {
            return true;
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }
}