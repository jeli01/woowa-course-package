package christmas.view;

import static java.lang.Integer.parseInt;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.Day;
import christmas.domain.Menu;
import christmas.domain.MenuCount;
import christmas.domain.OrderMenus;

public class Input {
    public static Day readVisitDay() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        String line = Console.readLine();
        try {
            Integer input = parseInt(line);
            Day day = new Day(input);
            return day;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    public static OrderMenus readMenu() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        String line = Console.readLine();
        OrderMenus orderMenus = new OrderMenus();
        try {
            String[] menuAndCounts = line.split(",");
            fillOrderMenus(orderMenus, menuAndCounts);
            return orderMenus;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private static void fillOrderMenus(OrderMenus orderMenus, String[] menuAndCounts) {
        for (String menuAndCount : menuAndCounts) {
            String[] menuOrCount = menuAndCount.split("-");
            validateLength(menuOrCount);
            orderMenus.addMenu(Menu.translate(menuOrCount[0]), new MenuCount(parseInt(menuOrCount[1])));
        }
    }

    private static void validateLength(String[] menuOrCount) {
        if (menuOrCount.length > 2) {
            throw new IllegalArgumentException("'항목-항목'의 형식이 아닙니다.");
        }
    }
}
