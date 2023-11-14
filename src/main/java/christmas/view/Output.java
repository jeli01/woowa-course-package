package christmas.view;

import christmas.domain.Menu;
import christmas.domain.MenuCount;
import java.util.Map;
import java.util.Set;

public class Output {

    public static void printProgramIntroduction() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public static void printEventBenefitIntroduction() {
        System.out.println("12월 26일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
        System.out.println();
    }

    public static void printOrderMenu(Map<Menu, MenuCount> orderMenus) {
        System.out.println("<주문 메뉴>");
        Set<Menu> menus = orderMenus.keySet();
        for (Menu menu : menus) {
            System.out.println(menu.getName() + " " + orderMenus.get(menu).getCount() + "개");
        }
        System.out.println();
    }
}
