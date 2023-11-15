package christmas.view;

import static christmas.domain.Badge.SANTA;
import static christmas.domain.Badge.STAR;
import static christmas.domain.Badge.TREE;

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

    public static void printEventBenefitIntroduction(Integer day) {
        System.out.println("12월 " + day + "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
    }

    public static void printOrderMenu(Map<Menu, MenuCount> orderMenus) {
        System.out.println("<주문 메뉴>");
        Set<Menu> menus = orderMenus.keySet();
        for (Menu menu : menus) {
            System.out.println(menu.getName() + " " + orderMenus.get(menu).getRawCount() + "개");
        }
    }

    public static void printTotalOrderPrice(Integer totalPrice) {
        System.out.println("<할인 전 총주문 금액>");
        String money = df.format(totalPrice);
        System.out.println(money + "원");
    }

    public static void printGiftMenu(String giftName, Integer count) {
        System.out.println("<증정 메뉴>");
        if (count > 0) {
            System.out.println(giftName + " " + count + "개");
            return;
        }
        printNon();
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

    public static void printWeekDiscount(Boolean isWeekend, Integer discount) {
        if (discount <= 0) {
            return;
        }
        if (isWeekend == true) {
            System.out.println("주말 할인: -" + df.format(discount) + "원");
            return;
        }
        System.out.println("평일 할인: -" + df.format(discount) + "원");
    }

    public static void printSpecialDiscount(Integer discount) {
        if (discount <= 0) {
            return;
        }
        System.out.println("특별 할인: -" + df.format(discount) + "원");
    }

    public static void printGiftEvent(Integer price, Integer count) {
        if (count < 1) {
            return;
        }
        System.out.println("증정 이벤트: -" + df.format(price * count) + "원");
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
        if (badge == STAR) {
            System.out.print("별");
            return;
        }
        if (badge == TREE) {
            System.out.print("트리");
            return;
        }
        if (badge == SANTA) {
            System.out.print("산타");
            return;
        }
        printNonNoNewLine();
    }

    public static void printNon() {
        System.out.println("없음");
    }

    public static void printNonNoNewLine() {
        System.out.print("없음");
    }
}
