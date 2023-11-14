package christmas.view;

import static christmas.domain.Gift.CHAMPAGNE;

import christmas.domain.Badge;
import christmas.domain.Menu;
import christmas.domain.MenuCount;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.Set;

public class Output {
    private static final DecimalFormat df = new DecimalFormat("###,###");

    public static void printProgramIntroduction() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public static void printEventBenefitIntroduction() {
        System.out.println("12월 26일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
    }

    public static void printOrderMenu(Map<Menu, MenuCount> orderMenus) {
        System.out.println("<주문 메뉴>");
        Set<Menu> menus = orderMenus.keySet();
        for (Menu menu : menus) {
            System.out.println(menu.getName() + " " + orderMenus.get(menu).getCount() + "개");
        }
    }

    public static void printTotalOrderPrice(Integer totalPrice) {
        System.out.println("<할인 전 총주문 금액>");
        String money = df.format(totalPrice);
        System.out.println(money + "원");
    }

    public static void printGiftMenu(Boolean isPossible) {
        System.out.println("<증정 메뉴>");
        if (isPossible) {
            System.out.println("샴페인 1개");
            return;
        }
        System.out.println("없음");
    }

    public static void printBenefitTitle() {
        System.out.println("<혜택 내역>");
    }

    public static void printChristmasDDayDiscount(Integer discount) {
        if (discount <= 0) {
            return;
        }
        System.out.println("크리스마스 디데이 할인: -" + df.format(discount) + "원");
    }

    public static void printWeekDiscount(Integer discount) {
        if (discount <= 0) {
            return;
        }
        System.out.println("평일 할인: -" + df.format(discount) + "원");
    }

    public static void printSpecialDiscount(Integer discount) {
        if (discount <= 0) {
            return;
        }
        System.out.println("크리스마스 디데이 할인: -" + df.format(discount) + "원");
    }

    public static void printGiftEvent(Boolean isPossible) {
        if (isPossible == false) {
            return;
        }
        System.out.println("크리스마스 디데이 할인: -" + df.format(CHAMPAGNE.getPrice()) + "원");
    }

    public static void printTotalBenefit(Integer totalBenefit) {
        System.out.println("<총혜택 금액>");
        if (totalBenefit > 0) {
            System.out.print("-");
        }
        System.out.println(df.format(totalBenefit) + "원");
    }

    public static void printPayment(Integer payment) {
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.println(df.format(payment) + "원");
    }

    public static void printBadge(Badge badge) {
        System.out.println("<12월 이벤트 배지>");
        if (badge == Badge.STAR) {
            System.out.println("별");
            return;
        }
        if (badge == Badge.TREE) {
            System.out.println("트리");
            return;
        }
        if (badge == Badge.SANTA) {
            System.out.println("산타");
            return;
        }
        System.out.println("없음");
    }

    public static void printNon() {
        System.out.println("없음");
    }
}
